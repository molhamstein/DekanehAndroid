package com.socket.dekaneh.activity.order_details;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.androidnetworking.error.ANError;
import com.google.gson.JsonObject;
import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.*;
import com.socket.dekaneh.utils.AppDateUtils;
import com.socket.dekaneh.utils.GsonUtils;

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
                                //Log.e("EEEEE", "accept: ", throwable);
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

    @Override
    public void cancelOrder() {
        requestDeleteOrder();
    }

    private void requestDeleteOrder() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.deleteOrder(getCacheStore().getSession().getAccessToken(), order.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<JsonObject>() {
                            @Override
                            public void accept(JsonObject s) throws Exception {
                                getView().finish();
                                getView().hideLoading();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                handleApiError((ANError) throwable);
                                getView().hideLoading();
                            }
                        })
        );
    }

    @Override
    public boolean checkOrderCancelOptionAvailable() {
        try {
            // is cancel order option still available
//            String string1 = "08:00:00";
//            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
//            calendar1.setTime(time1);

            Date d = order.getOrderDate();
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.set(Calendar.HOUR_OF_DAY,8);
            calendar3.set(Calendar.MINUTE,0);
            calendar3.set(Calendar.SECOND,0);

            //calendar3.add(Calendar.HOUR, 24);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime())) {
                return true;
            }
        } catch (Exception e){

        }
        return false;

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
