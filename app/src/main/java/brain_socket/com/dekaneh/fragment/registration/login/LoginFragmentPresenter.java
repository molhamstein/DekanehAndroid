package brain_socket.com.dekaneh.fragment.registration.login;


import android.util.Log;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class LoginFragmentPresenter<T extends LoginFragmentVP.View> extends BasePresenterImpl<T> implements LoginFragmentVP.Presenter<T>{


    private static final String TAG = LoginFragmentPresenter.class.getSimpleName();

    @Inject
    public LoginFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(schedulerProvider, compositeDisposable);
    }

    @Override
    public void login(String phoneNumber, String password) {

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            getView().onError(R.string.empty_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getView().onError(R.string.empty_password);
            return;
        }

        getView().showLoading();

        getCompositeDisposable().add(
                AppApiHelper.login(new LoginRequest("00963933074900", "123456"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        getView().showMessage(loginResponse.getUser().getOwnerName());
                        getView().hideLoading();
                        getView().startMainActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: ", throwable);
                        getView().hideLoading();
                    }
                })
        );
    }
}