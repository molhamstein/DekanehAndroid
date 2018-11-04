package brain_socket.com.dekaneh.fragment.registration.login;


import android.util.Log;

import com.androidnetworking.error.ANError;
import com.onesignal.OneSignal;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import brain_socket.com.dekaneh.network.model.User;
import brain_socket.com.dekaneh.utils.NetworkUtils;
import brain_socket.com.dekaneh.utils.ValidationUtils;
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
        }else if(!ValidationUtils.isValidPhoneNumber(phoneNumber)) {
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
                                    OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                                        @Override
                                        public void idsAvailable(String userId, String registrationId) {
                                            OneSignal.sendTag("user_id", loginResponse.getUser().getId());
                                        }

                                    });
                                }
                                else {
                                    getView().showMessage("Your account needs to be activated");
                                    getView().hideLoading();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "accept: ", throwable);
                                getView().hideLoading();
                                if (throwable instanceof ANError) {
                                    ANError error = (ANError) throwable;
                                    getView().hideLoading();
                                    getView().showMessage(NetworkUtils.getError(throwable));

                                }
                            }
                        })
        );
    }


}