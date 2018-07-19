package brain_socket.com.dekaneh.fragment;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.error.ANError;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginFragment extends BaseFragment {

    public LoginFragment() { }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {


    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public String TAG() {
        return LoginFragment.class.getSimpleName();
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.loginBtn)
    public void onLoginBtnClicked(){
        AppApiHelper.login(new LoginRequest("0938093917", "123qweasd"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        Log.d(TAG(), "accept: " + loginResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG(), "accept: ", throwable);
                        if (throwable instanceof ANError){
                            ANError error = (ANError) throwable;
                            Log.d(TAG(), "accept: " + error.getErrorCode() + " " + error.getErrorBody());
                        }
                    }
                });
    }

    @OnClick(R.id.signUpText)
    public void onSignUpTextClicked(){
        navigationPresenter.replaceFragment(ChooseAccountFragment.newInstance());
    }

    @OnClick(R.id.forgotPasswordText)
    public void onForgotPasswordText(){
        navigationPresenter.replaceFragment(ForgotPasswordFragment.newInstance());
    }
}
