package com.socket.dekaneh.activity.splash;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.CacheStore;
import io.reactivex.disposables.CompositeDisposable;

public class SplashActivityPresenter<T extends SplashActivityVP.View> extends BasePresenterImpl<T> implements SplashActivityVP.Presenter<T> {

    @Inject
    public SplashActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void clearCache() {
        getCacheStore().clearCacheWithoutCart();
    }
}
