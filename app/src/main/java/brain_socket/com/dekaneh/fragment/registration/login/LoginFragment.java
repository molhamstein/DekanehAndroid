package brain_socket.com.dekaneh.fragment.registration.login;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.florent37.viewanimator.ViewAnimator;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.MainActivity;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.fragment.registration.ChooseAccountFragment;
import brain_socket.com.dekaneh.fragment.registration.ForgotPasswordFragment;
import brain_socket.com.dekaneh.fragment.registration.NewAccountFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginFragmentVP.View {

    @Inject
    LoginFragmentVP.Presenter<LoginFragmentVP.View> presenter;

    @BindView(R.id.loginPhoneNumber)
    EditText phoneNumberEditText;
    @BindView(R.id.loginPassword)
    EditText passwordEditText;
    @BindView(R.id.loginPhoneCard)
    View phoneNumberCard;
    @BindView(R.id.loginPasswordCard)
    View passwordCard;
    @BindView(R.id.forgotPasswordText)
    View forgotPasswordText;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.loginSignUpText)
    View signUpText;

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

        ViewAnimator.animate(phoneNumberCard).translationX(-800, 0)
                .alpha(0f, 1f).duration(1000)
                .andAnimate(passwordCard).translationX(-1000, 0)
                .alpha(0f, 1f).duration(1000)
                .andAnimate(forgotPasswordText).translationX(-1100, 0)
                .alpha(0, 1).duration(1000)
                .andAnimate(loginBtn).translationX(-1200, 0)
                .alpha(0, 1).duration(1000)
                .andAnimate(signUpText).translationX(-1300, 0)
                .alpha(0, 1).duration(1000)
                .start();

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
        navigationPresenter.replaceFragment(NewAccountFragment.newInstance());
    }

    @OnClick(R.id.forgotPasswordText)
    public void onForgotPasswordText(){
        navigationPresenter.replaceFragment(ForgotPasswordFragment.newInstance());
    }

    @Override
    public void startMainActivity() {
        MainActivity.start(getContext());
    }
}
