package brain_socket.com.dekaneh.fragment;


import android.annotation.SuppressLint;
import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import butterknife.OnClick;

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
