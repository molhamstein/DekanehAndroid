package com.socket.dekaneh.fragment.profile;

import android.util.Log;

import com.androidnetworking.error.ANError;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.NetworkUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ProfileFragmentPresenter<T extends ProfileFragmentVP.View> extends BasePresenterImpl<T> implements ProfileFragmentVP.Presenter<T> {

    public static final String TAG = ProfileFragmentPresenter.class.getSimpleName();

    @Inject
    public ProfileFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        getView().updateView(getCacheStore().getSession().getShopName(),
                getCacheStore().getSession().getOwnerName(),
                getCacheStore().getSession().getPhoneNumber());
        getView().updateMap(NetworkUtils.getStaticMapUrl(getCacheStore().getSession().getLatitude(), getCacheStore().getSession().getLongitude()));

    }

    @Override
    public void fetchOrders() {
        Log.d(TAG, "fetchOrders: start");
        if (isNetworkConnected()) {
            Log.d(TAG, "fetchOrders: fetching");
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getCurrentOrders(getCacheStore().getSession().getUserId(), getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<Order>>() {
                                @Override
                                public void accept(List<Order> orders) throws Exception {

                                    getView().hideLoading();
                                    getView().addOrders(orders);
                                    Log.d(TAG, "accept: " + orders.toString());

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    getView().hideLoading();
                                    Log.e(TAG, "accept: ", throwable);
                                    handleApiError((ANError) throwable);
                                }
                            })
            );
        }
    }

    @Override
    public void patchUser(String storeName, String ownerName, String phoneNumber) {
        if (isNetworkConnected()) {

            User user = getCacheStore().getSession().getUser();
            user.setPhoneNumber(phoneNumber);
            user.setOwnerName(ownerName);
            user.setShopName(storeName);

            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.patchUser(user, getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<User>() {
                                @Override
                                public void accept(User user) throws Exception {
                                    getView().updateView(user.getShopName(), user.getOwnerName(), user.getPhoneNumber());
                                    getCacheStore().getSession().setUser(user);
                                    getView().hideLoading();
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
    }

    @Override
    public void fetchPastOrders() {
        if (isNetworkConnected()) {

            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getPastOrders(getCacheStore().getSession().getUserId(), getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<Order>>() {
                                @Override
                                public void accept(List<Order> orders) throws Exception {

                                    getView().hideLoading();
                                    getView().addOrders(orders);

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    getView().hideLoading();
                                    Log.e(TAG, "accept: ", throwable);
                                    handleApiError((ANError) throwable);
                                }
                            })
            );
        }
    }

    @Override
    public boolean hideHistory() {
        return getCacheStore().getSession().getHideHistory();
    }
}
