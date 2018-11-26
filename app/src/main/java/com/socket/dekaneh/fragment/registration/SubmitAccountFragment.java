package com.socket.dekaneh.fragment.registration;


import android.view.View;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import com.socket.dekaneh.R;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.custom.DekanehInterpolator;
import com.socket.dekaneh.fragment.registration.login.LoginFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class SubmitAccountFragment extends BaseFragment {

    @BindView(R.id.submitAccountText1)
    View text1;
    @BindView(R.id.submitAccountText2)
    View text2;
    @BindView(R.id.submitAccountImage)
    View envelopeImage;
    @BindView(R.id.submitAccountLoginBtn)
    View backToLoginBtn;

    public SubmitAccountFragment() { }

    public static SubmitAccountFragment newInstance() {
        SubmitAccountFragment fragment = new SubmitAccountFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        ViewAnimator.animate(text1).translationX(-600, 0).alpha(0, 1).duration(800)
                .andAnimate(text2).translationX(-700, 0).alpha(0, 1).duration(800)
                .andAnimate(backToLoginBtn).translationX(-900, 0).alpha(0, 1).duration(800)
                .andAnimate(envelopeImage).slideRight().fadeIn()
                .interpolator(new DekanehInterpolator(1f))
                .start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_submit_account;
    }

    @Override
    public String TAG() {
        return SubmitAccountFragment.class.getSimpleName();
    }

    @OnClick(R.id.submitAccountLoginBtn)
    public void onLoginBtnClicked() {
        performOutAnimation(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                navigationPresenter.replaceFragment(LoginFragment.newInstance());
            }
        });
    }

    private void performOutAnimation(AnimationListener.Stop onStop) {
        ViewAnimator.animate(text1).translationX(0, 600).alpha(1, 0).duration(600)
                .andAnimate(text2).translationX(0, 700).alpha(1, 0).duration(600)
                .andAnimate(envelopeImage).translationX(0, 800).alpha(1, 0).duration(600)
                .andAnimate(backToLoginBtn).translationX(0, 900).alpha(1, 0).duration(600)
                .interpolator(new DekanehInterpolator(1f))
                .onStop(onStop)
                .start();
    }

}
