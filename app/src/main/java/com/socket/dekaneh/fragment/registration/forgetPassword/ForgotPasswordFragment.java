package com.socket.dekaneh.fragment.registration.forgetPassword;


import android.view.View;
import android.widget.EditText;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import com.socket.dekaneh.R;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.custom.DekanehInterpolator;
import com.socket.dekaneh.fragment.registration.login.LoginFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordFragment extends BaseFragment {

    @Inject
    ForgetPasswordVP.Presenter<ForgetPasswordVP.View> presenter;

    @BindView(R.id.forgetPasswordSubmitBtn)
    View submitBtn;
    @BindView(R.id.forgotPassPhoneCard)
    View phoneCard;
    @BindView(R.id.forgotPassText1)
    View text1;
    @BindView(R.id.forgotPassText2)
    View text2;
    @BindView(R.id.phoneNumber)
    EditText phoneNumber;

    public ForgotPasswordFragment() { }

    public static ForgotPasswordFragment newInstance(){
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }

    @Override
    public void init(View rootView) {

        ViewAnimator.animate(text1).slideLeft().fadeIn()
                .andAnimate(text2).slideLeft().fadeIn()
                .andAnimate(phoneCard).translationX(-600, 0).alpha(0, 1)
                .duration(800)
                .andAnimate(submitBtn).translationX(-700, 0).alpha(0, 1)
                .duration(800)
                .interpolator(new DekanehInterpolator(1f))
                .start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public String TAG() {
        return ForgotPasswordFragment.class.getSimpleName();
    }

    @OnClick(R.id.forgetPasswordSubmitBtn)
    public void onsubmitBtnClicked() {
        performOutAnimation(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                presenter.forgetPasswordRequest(phoneNumber.getText().toString());
            }
        });
    }

    private void performOutAnimation(AnimationListener.Stop onStop) {
        ViewAnimator.animate(text1).translationX(0, 600).alpha(1, 0)
                .andAnimate(text2).translationX(0, 600).alpha(1, 0)
                .andAnimate(phoneCard).translationX(0, 600).alpha(1, 0)
                .duration(800)
                .andAnimate(submitBtn).translationX(0, 700).alpha(1, 0)
                .duration(600)
                .interpolator(new DekanehInterpolator(1f))
                .onStop(onStop)
                .start();
    }
}
