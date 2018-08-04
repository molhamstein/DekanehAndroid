package brain_socket.com.dekaneh.fragment.registration;


import android.view.View;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.custom.DekanehInterpolator;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class RestorePasswordFragment extends BaseFragment {


    @BindView(R.id.restorePassText1)
    View text1;
    @BindView(R.id.restorePassText2)
    View text2;
    @BindView(R.id.restorePassPhoneCard)
    View phoneCard;
    @BindView(R.id.restorePassNewPassCard)
    View newPasswordCard;

    public RestorePasswordFragment() { }


    @Override
    public void init(View rootView) {

        ViewAnimator.animate(text1).translationX(-800, 0)
                .alpha(0f, 1f).duration(800)
                .andAnimate(text2).translationX(-1000, 0)
                .alpha(0f, 1f).duration(800)
                .andAnimate(phoneCard).translationX(-1100, 0)
                .alpha(0, 1).duration(800)
                .andAnimate(newPasswordCard).translationX(-1200, 0)
                .alpha(0, 1).duration(800)
                .interpolator(new DekanehInterpolator(1f))
                .start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_restore_password;
    }

    @Override
    public String TAG() {
        return RestorePasswordFragment.class.getSimpleName();
    }

    @OnClick(R.id.restorePassLoginBtn)
    public void onLoginBtnClicked() {
        performOutAnimation(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                navigationPresenter.replaceFragment(LoginFragment.newInstance());
            }
        });
    }

    private void performOutAnimation(AnimationListener.Stop onStop) {
        ViewAnimator.animate(text1).translationX(0, 600)
                .alpha(1f, 0f).duration(600)
                .andAnimate(text2).translationX(0, 700)
                .alpha(1f, 0f).duration(600)
                .andAnimate(phoneCard).translationX(0, 800)
                .alpha(1f, 0f).duration(600)
                .andAnimate(newPasswordCard).translationX(0, 900)
                .alpha(1f, 0f).duration(600)
                .interpolator(new DekanehInterpolator(1f))
                .onStop(onStop)
                .start();
    }

}
