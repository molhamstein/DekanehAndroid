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
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getCurrentOrders(getCacheStore().getSession().getUserId())
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
                    }
                })
        );
    }

    @Override
    public void patchUser() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.patchUser(getCacheStore().getSession().getUser(), getCacheStore().getSession().getAccessToken())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        Log.d(TAG, "accept: " + user.getOwnerName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: ", throwable);
                        if (throwable instanceof ANError) {
                            ANError error = (ANError) throwable;
                            Log.e(TAG, "accept: " + error.getErrorBody(), error);
                        }
                    }
                })
        );
    }

    @Override
    public void fetchPastOrders() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getPastOrders(getCacheStore().getSession().getUserId())
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
                            }
                        })
        );
    }
}
