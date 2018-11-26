package com.socket.dekaneh.activity.cart;

import android.util.Log;

import com.androidnetworking.error.ANError;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.network.model.OrderRequest;
import com.socket.dekaneh.network.model.Orderitem;
import com.socket.dekaneh.network.model.User;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CartActivityPresenter<T extends CartActivityVP.View> extends BasePresenterImpl<T> implements CartActivityVP.Presenter<T> {

    public static final String TAG = CartActivityPresenter.class.getSimpleName();

    @Inject
    public CartActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void fetchItems() {
        List<CartItem> items = getCacheStore().getCartItems();
        if (!items.isEmpty()) {
            getView().addAllItems(getCacheStore().getCartItems());
            getView().setOrderViewClear(false);
        } else getView().setOrderViewClear(true);

    }

    @Override
    public void sendOrder() {
        List<Orderitem> orderItems = new ArrayList<>();
        for (CartItem item : getCacheStore().getCartItems()) {
            orderItems.add(new Orderitem(item.getCount(), item.getId()));
        }

        OrderRequest order = new OrderRequest(getCacheStore().getSession().getUserId(), orderItems);

        Log.d(TAG, "sendOrder: " + order.toString());

        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.sendOrder(getCacheStore().getSession().getAccessToken(), order)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Order>() {
                            @Override
                            public void accept(Order order) throws Exception {

                                getCacheStore().clearCart();
                                getView().hideLoading();
                                getView().setOrderViewClear(true);
                                getView().setOkResult();

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "accept: ", throwable);
                                handleApiError((ANError) throwable);
                                getView().hideLoading();
                            }
                        })
        );

    }

    @Override
    public int getPrice() {
        int price = 0;
        for (CartItem item : getCacheStore().getCartItems()) {
            price += item.getTotalPrice(getCacheStore().getSession().getClientType().equals(User.Type.retailCostumer.toString()));
        }
        return price;
    }

}
