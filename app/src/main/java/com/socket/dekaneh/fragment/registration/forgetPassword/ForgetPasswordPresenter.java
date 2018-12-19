package com.socket.dekaneh.fragment.registration.forgetPassword;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ForgetPasswordPresenter<T extends ForgetPasswordVP.View> extends BasePresenterImpl<T> implements ForgetPasswordVP.Presenter<T> {

    @Inject
    public ForgetPasswordPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void forgetPasswordRequest(String phoneNumber) {

        if (ValidationUtils.isValidPhoneNumber(phoneNumber)) {

            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.forgetPassword(ValidationUtils.validatePhoneNumber(phoneNumber))
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    getView().hideLoading();
                                    getView().showMessage(R.string.forgetPasswordMsg);
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
        else {
            getView().showMessage(R.string.provide_valid_phone_number_statement);
        }
    }

}
