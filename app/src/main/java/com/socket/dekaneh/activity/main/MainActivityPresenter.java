package com.socket.dekaneh.activity.main;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

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
        getCacheStore().clearProductsCache();
        getView().recreate();
    }

    @Override
    public void search(String query) {
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
                        NetworkUtils.getError(throwable);
                        getView().hideLoading();
                        Log.e("ASD", "accept: " + NetworkUtils.getError(throwable), throwable);
                    }
                })
        );
    }

}