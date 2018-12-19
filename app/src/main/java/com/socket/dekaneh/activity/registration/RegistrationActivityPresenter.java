package com.socket.dekaneh.activity.registration;

import android.util.Log;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.CacheStore;
import io.reactivex.disposables.CompositeDisposable;

public class RegistrationActivityPresenter<T extends RegistrationActivityVP.View> extends BasePresenterImpl<T> implements RegistrationActivityVP.Presenter<T>, FragmentNavigationVP.Presenter{


    @Inject
    public RegistrationActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        if (getCacheStore().getSession().isLoggedOn()){
            Log.d("access token ", getCacheStore().getSession().getAccessToken());
            getView().startMainActivity();
            getView().finish();
        }
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        getView().setFragment(fragment);
    }
}
