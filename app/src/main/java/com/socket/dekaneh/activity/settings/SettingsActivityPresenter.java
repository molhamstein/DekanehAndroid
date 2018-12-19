package com.socket.dekaneh.activity.settings;

import android.content.Context;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.utils.ViewUtils;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsActivityPresenter<T extends SettingsActivityVP.View> extends BasePresenterImpl<T> implements SettingsActivityVP.Presenter<T> {

    public static final String TAG = SettingsActivityPresenter.class.getSimpleName();

    @Inject
    public SettingsActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }
//
//    @Override
//    public void logout() {
//
//        getView().showLoading();
//        getCompositeDisposable().add(
//                AppApiHelper.logout(getCacheStore().getSession().getAccessToken())
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String string) throws Exception {
//                        getView().hideLoading();
//                        getCacheStore().getSession().logout();
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e(TAG, "accept: ", throwable);
//                        getView().hideLoading();
//                    }
//                })
//        );
//
//    }

    @Override
    public void offlineLogout() {
        getCacheStore().getSession().logout();
    }

    @Override
    public void callSupport(Context context) {
        ViewUtils.makeCall(context, "00963947331047");
    }

    @Override
    public void openUrl(Context context) {
        ViewUtils.openUrl(context, "http://dockaan.com/privacy");
    }
}