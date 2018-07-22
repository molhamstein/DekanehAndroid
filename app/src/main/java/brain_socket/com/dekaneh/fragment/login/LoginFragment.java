package brain_socket.com.dekaneh.fragment.login;


import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.fragment.ChooseAccountFragment;
import brain_socket.com.dekaneh.fragment.ForgotPasswordFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginFragmentVP.View {

    @Inject
    LoginFragmentVP.Presenter<LoginFragmentVP.View> presenter;

    @BindView(R.id.loginPhoneNumber)
    EditText phoneNumberEditText;
    @BindView(R.id.loginPassword)
    EditText passwordEditText;


    public LoginFragment() { }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        presenter.onAttach(this);

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public String TAG() {
        return LoginFragment.class.getSimpleName();
    }

    @OnClick(R.id.loginBtn)
    public void onLoginBtnClicked(){
        presenter.login(phoneNumberEditText.getText().toString(), passwordEditText.getText().toString());
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
