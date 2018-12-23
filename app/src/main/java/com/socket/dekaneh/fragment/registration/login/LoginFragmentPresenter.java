package com.socket.dekaneh.fragment.registration.login;


import com.androidnetworking.error.ANError;
import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.LoginRequest;
import com.socket.dekaneh.network.model.LoginResponse;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class LoginFragmentPresenter<T extends LoginFragmentVP.View> extends BasePresenterImpl<T> implements LoginFragmentVP.Presenter<T> {


    private static final String TAG = LoginFragmentPresenter.class.getSimpleName();


    @Inject
    public LoginFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }


    @Override
    public void login(String phoneNumber, String password) {

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            getView().onError(R.string.empty_email);
            return;
        } else if (!ValidationUtils.isValidPhoneNumber(phoneNumber)) {
            getView().onError(R.string.provide_valid_phone_number_statement);
            return;
        }

        if (password == null || password.isEmpty()) {
            getView().onError(R.string.empty_password);
            return;
        }

        getView().showLoading();
        getView().hideKeyboard();

        getCompositeDisposable().add(
                AppApiHelper.login(new LoginRequest(ValidationUtils.validatePhoneNumber(phoneNumber), password))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<LoginResponse>() {
                            @Override
                            public void accept(final LoginResponse loginResponse) throws Exception {
                                if (loginResponse.getUser().getStatus() == User.Status.activated) {
                                    getCacheStore().getSession().setUser(loginResponse.getUser(), loginResponse.getId());
                                    getView().hideLoading();
                                    getView().startMainActivity();
                                    getView().finish();
                                } else {
                                    getView().showMessage(R.string.activate_account_error);
                                    getView().hideLoading();
                                }
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


}