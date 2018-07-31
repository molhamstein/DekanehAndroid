package brain_socket.com.dekaneh.fragment.registration;


import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import butterknife.BindView;

public class ForgotPasswordFragment extends BaseFragment {

    @BindView(R.id.forgetPasswordSubmitBtn)
    View submitBtn;
    @BindView(R.id.forgotPassPhoneCard)
    View phoneCard;

    public ForgotPasswordFragment() { }

    public static ForgotPasswordFragment newInstance(){
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }

    @Override
    public void init(View rootView) {

        ViewAnimator.animate(phoneCard)
                .translationX(-800, 0).alpha(0, 1)
                .duration(800)
                .andAnimate(submitBtn).translationX(-800, 0)
                .duration(800)
                .alpha(0, 1).decelerate().start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public String TAG() {
        return ForgotPasswordFragment.class.getSimpleName();
    }

}
