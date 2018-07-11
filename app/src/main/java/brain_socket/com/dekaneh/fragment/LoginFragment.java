package brain_socket.com.dekaneh.fragment;


import android.view.View;

import brain_socket.com.dekaneh.R;
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

    @OnClick(R.id.signUpText)
    public void onSignUpTextClicked(){
        navigationPresenter.replaceFragment(ChooseAccountFragment.newInstance());
    }

    @OnClick(R.id.forgotPasswordText)
    public void onForgotPasswordText(){
        navigationPresenter.replaceFragment(ForgotPasswordFragment.newInstance());
    }
}
