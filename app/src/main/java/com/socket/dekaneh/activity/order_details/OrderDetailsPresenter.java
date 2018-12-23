package com.socket.dekaneh.activity.order_details;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Coupon;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.network.model.OrderRequest;
import com.socket.dekaneh.network.model.Orderitem;
import com.socket.dekaneh.utils.AppDateUtils;
import com.socket.dekaneh.utils.GsonUtils;
import com.socket.dekaneh.utils.NetworkUtils;

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
                AppApiHelper.getOrderDetails(order.getId(), getCacheStore().getSession().getAccessToken())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Order>() {
                            @Override
                            public void accept(Order order) throws Exception {
                                getView().updateAllItems(order.getItems());
                                getView().updateView(String.valueOf(order.getId()), AppDateUtils.dateToString(order.getOrderDate()),
                                        getStuats(order.getStatus()), String.valueOf(order.getTotalPrice()));
                                if (order.isCouponExist()) {
                                    String couponValue = order.getCoupon().getType() == Coupon.Type.fixed ? String.valueOf(order.getCoupon().getValue()) : String.valueOf(order.getCoupon().getValue() + " %");
                                    getView().addCoupon(order.getCoupon().getCode(), couponValue, String.valueOf(order.getPriceBeforeCoupon()));
                                }
                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                handleApiError((ANError) throwable);

                            }
                        })
        );
    }

    @Override
    public void updateOrder() {
        getView().showLoading();

        List<Orderitem> orderItems = new ArrayList<>();
        for (CartItem item : this.order.getItems()) {
            orderItems.add(new Orderitem(item.getCount(), item.getProductId()));
        }

        OrderRequest order = new OrderRequest(getCacheStore().getSession().getUserId(), orderItems);


        getCompositeDisposable().add(
                AppApiHelper.patchOrder(getCacheStore().getSession().getAccessToken(), order, this.order.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Order>() {
                            @Override
                            public void accept(Order order) throws Exception {
                                getView().finish();
                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("EEEEE", "accept: ", throwable);
                                handleApiError((ANError) throwable);
                                getView().hideLoading();
                            }
                        })
        );
    }

    @Override
    public void updateProducts(List<CartItem> items) {
        this.order.setProducts(items);
    }


    private String getStuats(Order.Status status) {
        switch (status) {
            case pending:
                return getView().getContext().getString(R.string.pending);
            case inDelivery:
                return getView().getContext().getString(R.string.in_delivery);
            case delivered:
                return getView().getContext().getString(R.string.delivered);
            default:
                return getView().getContext().getString(R.string.calnceled);
        }
    }
}
