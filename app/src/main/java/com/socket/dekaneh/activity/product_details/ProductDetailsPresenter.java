package com.socket.dekaneh.activity.product_details;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.GsonUtils;
import com.socket.dekaneh.utils.NetworkUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ProductDetailsPresenter<T extends ProductDetailsVP.View> extends BasePresenterImpl<T> implements ProductDetailsVP.Presenter<T> {

    // in some cases only id is valid. Every other atts are null.
    private Product product;
    private CartItem item;

    @Inject
    public ProductDetailsPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        Log.d("DADADA", "onAttach: " + getView().getIntent().getExtras().getString(Product.TAG));
        this.product = GsonUtils.convertJsonStringToProductObject(getView().getIntent().getExtras().getString(Product.TAG));
        fetchProduct();
        fetchOffers();
        fetchSimilarProducts();
    }


    @Override
    public void fetchProduct() {

        getView().showLoading();

        getCompositeDisposable().add(
                AppApiHelper.getProduct(product)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Product>() {
                            @Override
                            public void accept(Product product) throws Exception {
                                String imageUrl = ProductDetailsPresenter.this.product.getMedia().getUrl();
                                item = new CartItem(ProductDetailsPresenter.this.product);
                                getView().updateOrderCountText(getCacheStore().cartItemCount(item));
                                ProductDetailsPresenter.this.product = product;
                                getView().updateView(product, imageUrl, getCacheStore().getSession().getClientType().equals(User.Type.retailCostumer));
                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                Log.e("", "accept: " + NetworkUtils.getError(throwable));
                            }
                        })
        );
    }

    @Override
    public void fetchOffers() {
        getView().showLoading();

        getCompositeDisposable().add(
                AppApiHelper.getProductOffers(product.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Offer>>() {
                            @Override
                            public void accept(List<Offer> offers) throws Exception {

                                if (offers.isEmpty()) getView().hideOffersSection();
                                else getView().addAllOffers(offers);
                                getView().hideLoading();

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("", "accept: " + NetworkUtils.getError(throwable), throwable);

                            }
                        })
        );
    }

    @Override
    public void fetchSimilarProducts() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getSimilarProducts(product.getId())
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
                                Log.e("", "accept: " + NetworkUtils.getError(throwable));
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