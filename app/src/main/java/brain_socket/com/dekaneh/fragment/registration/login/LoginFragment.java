package brain_socket.com.dekaneh.fragment.registration.login;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.main.MainActivity;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.custom.DekanehInterpolator;
import brain_socket.com.dekaneh.fragment.registration.ForgotPasswordFragment;
import brain_socket.com.dekaneh.fragment.registration.new_account.NewAccountFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginFragmentVP.View {

    @Inject
    LoginFragmentVP.Presenter<LoginFragmentVP.View> presenter;

    @BindView(R.id.loginLogo)
    View logo;
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

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        presenter.onAttach(this);

        ViewAnimator.animate(logo).slideLeft().fadeIn()
                .andAnimate(phoneNumberCard).translationX(-800, 0)
                .alpha(0f, 1f).duration(800)
                .andAnimate(passwordCard).translationX(-1000, 0)
                .alpha(0f, 1f).duration(800)
                .andAnimate(forgotPasswordText).translationX(-1100, 0)
                .alpha(0, 1).duration(800)
                .andAnimate(loginBtn).translationX(-1200, 0)
                .alpha(0, 1).duration(800)
                .andAnimate(signUpText).translationX(-1300, 0)
                .alpha(0, 1).duration(800)
                .interpolator(new DekanehInterpolator(1f))
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
    public void onLoginBtnClicked() {
        presenter.login(phoneNumberEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.signUpText)
    public void onSignUpTextClicked() {
        performOutAnimation(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                navigationPresenter.replaceFragment(NewAccountFragment.newInstance());
            }
        });
    }

    @OnClick(R.id.forgotPasswordText)
    public void onForgotPasswordText() {
        performOutAnimation(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                navigationPresenter.replaceFragment(ForgotPasswordFragment.newInstance());
            }
        });
    }

    @Override
    public void startMainActivity() {
        MainActivity.start(getContext());
    }

    private void performOutAnimation(AnimationListener.Stop onStop) {
        ViewAnimator.animate(logo).translationX(0, 600)
                .alpha(1f, 0f).duration(600)
                .andAnimate(phoneNumberCard).translationX(0, 600)
                .alpha(1f, 0f).duration(600)
                .andAnimate(passwordCard).translationX(0, 700)
                .alpha(1f, 0f).duration(600)
                .andAnimate(forgotPasswordText).translationX(0, 800)
                .alpha(1f, 0f).duration(600)
                .andAnimate(loginBtn).translationX(0, 900)
                .alpha(1f, 0f).duration(600)
                .andAnimate(signUpText).translationX(0, 1000)
                .alpha(1f, 0f).duration(600)
                .interpolator(new DekanehInterpolator(1f))
                .onStop(onStop)
                .start();
    }
}
