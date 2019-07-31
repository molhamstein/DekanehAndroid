package com.socket.dekaneh.activity.main;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import com.androidnetworking.error.ANError;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.utils.NetworkUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivityPresenter<T extends MainActivityVP.View> extends BasePresenterImpl<T> implements MainActivityVP.Presenter<T> {




    @Inject
    public MainActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        Boolean showProfile  = getView().getIntent().getBooleanExtra("showProfile",false);
        if (showProfile)
            getView().showProfileFragment();
        else
            getView().showMainFragment();
        getView().showToolbarTitle(false);
        getView().showToolbarLogo(true);

    }

    @Override
    public void onBottomNavMainItemClick() {
        getView().showMainFragment();
        getView().showToolbarTitle(false);
        getView().showToolbarLogo(true);
    }

    @Override
    public void onBottomOffersItemClick() {
        getView().showOffersFragment();
        getView().showToolbarLogo(false);
        getView().showToolbarTitle(true);
        getView().setToolbarTitle(R.string.offers);
    }

    @Override
    public void onBottomCategoriesItemClick() {
        getView().showCategoriesFragment();
        getView().showToolbarLogo(false);
        getView().showToolbarTitle(true);
        getView().setToolbarTitle(R.string.categories);
    }

    @Override
    public void onBottomProfileItemClick() {
        getView().showProfileFragment();
        getView().showToolbarLogo(false);
        getView().showToolbarTitle(true);
        getView().setToolbarTitle(R.string.profile);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void restart() {
//        getCacheStore().clearProductsCache();
        getView().recreate();
    }

    @Override
    public void search(String query) {
        if (isNetworkConnected()) {
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.search(query, getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<Product>>() {
                                @Override
                                public void accept(List<Product> products) throws Exception {
                                    getView().updateSearchView(products);
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
    }

    @Override
    public void checkUserActivated() {
        if (isNetworkConnected()) {
            getView().showLoading();
            getCompositeDisposable().add(AppApiHelper.isActivated(getCacheStore().getSession().getAccessToken())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {

                            if (!aBoolean) {
                                getCacheStore().getSession().logout();
                            }
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
    }

    @Override
    public void updateFirebaseToken() {
        if (isNetworkConnected()) {

            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String newToken = instanceIdResult.getToken();
                    Log.d("ASDADQWEASDQWEASD", "onSuccess: " + newToken);
                    getCompositeDisposable().add(AppApiHelper.putFirebaseToken(getCacheStore().getSession().getAccessToken(), newToken)
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    handleApiError((ANError) throwable);
                                }
                            }));
                }
            });

        }
    }

}