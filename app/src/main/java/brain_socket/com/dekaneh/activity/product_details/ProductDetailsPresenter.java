package brain_socket.com.dekaneh.activity.product_details;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.GsonUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ProductDetailsPresenter<T extends ProductDetailsVP.View> extends BasePresenterImpl<T> implements ProductDetailsVP.Presenter<T> {

    private Product product;
    private CartItem item;

    @Inject
    public ProductDetailsPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        this.product = GsonUtils.convertJsonStringToProductObject(getView().getIntent().getExtras().getString(Product.TAG));
        getView().updateView(product);
        fetchSimilarProducts(product.getId());
        item = new CartItem(product);
    }

    @Override
    public void fetchSimilarProducts(String id) {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getSimilarProducts(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Product>>() {
                            @Override
                            public void accept(List<Product> products) throws Exception {
                                if (products.isEmpty()) getView().hideSimilarProductsSection();
                                else getView().addAllSimilarProducts(products);

                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                            }
                        })
        );
    }

    @Override
    public void onMinusBtnClicked() {
        if (getCacheStore().cartItemCount(item) > 0)
            getCacheStore().removeCartItem(item);
        getView().updateOrderCountText(getCacheStore().cartItemCount(item));
    }

    @Override
    public void onPlusBtnClicked() {
        getCacheStore().addCartItem(item);
        getView().updateOrderCountText(getCacheStore().cartItemCount(item));
    }
}