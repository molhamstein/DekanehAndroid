package brain_socket.com.dekaneh.activity.order_details;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Order;
import brain_socket.com.dekaneh.utils.AppDateUtils;
import brain_socket.com.dekaneh.utils.GsonUtils;
import brain_socket.com.dekaneh.utils.NetworkUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class OrderDetailsPresenter<T extends OrderDetailsVP.View> extends BasePresenterImpl<T> implements OrderDetailsVP.Presenter<T> {

    private Order order;

    @Inject
    public OrderDetailsPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        this.order = GsonUtils.convertJsonStringToOrderObject(getView().getIntent().getExtras().getString(Order.TAG));

    }

    @Override
    public void fetchItems() {
        getView().showLoading();

        getCompositeDisposable().add(
                AppApiHelper.getOrderDetails(order.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Order>() {
                            @Override
                            public void accept(Order order) throws Exception {
                                getView().updateAllItems(order.getItems());
                                getView().updateView(String.valueOf(order.getId()), AppDateUtils.dateToString(order.getOrderDate()),
                                        order.getStatus().toString(), String.valueOf(order.getTotalPrice()));
                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                getView().showMessage(NetworkUtils.getError(throwable));

                            }
                        })
        );
    }

    @Override
    public void updateOrder() {
        getView().showLoading();

        getCompositeDisposable().add(
                AppApiHelper.patchOrder(order)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Order>() {
                            @Override
                            public void accept(Order order) throws Exception {
                                getView().updateAllItems(order.getItems());
                                getView().updateView(String.valueOf(order.getId()), AppDateUtils.dateToString(order.getOrderDate()),
                                        order.getStatus().toString(), String.valueOf(order.getTotalPrice()));
                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("EEEEE", "accept: ", throwable);
                                getView().hideLoading();
                            }
                        })
        );
    }

    @Override
    public void updateProducts(List<CartItem> items) {
        this.order.setProducts(items);
    }
}
