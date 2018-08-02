package brain_socket.com.dekaneh.fragment.registration;


import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class NewAccountFragment extends BaseFragment {

    @BindView(R.id.newAccountPhoneCard)
    View phoneCard;
    @BindView(R.id.newAccountStoreNameCard)
    View storeNameCard;
    @BindView(R.id.newAccountAddress)
    View storeAddressCard;
    @BindView(R.id.newAccountPasswordCard)
    View passwordCard;
    @BindView(R.id.newAccountSubmitBtn)
    View submitBtn;

    public NewAccountFragment() {
    }

    public static NewAccountFragment newInstance() {
        NewAccountFragment fragment = new NewAccountFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        ViewAnimator.animate(phoneCard).translationX(-600, 0).alpha(0, 1).duration(800)
                .andAnimate(storeNameCard).translationX(-700, 0).alpha(0, 1).duration(800)
                .andAnimate(storeAddressCard).translationX(-800, 0).alpha(0, 1).duration(800)
                .andAnimate(passwordCard).translationX(-900, 0).alpha(0, 1).duration(800)
                .andAnimate(submitBtn).translationX(-1000, 0).alpha(0, 1).duration(800)
                .decelerate().start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_new_account;
    }

    @Override
    public String TAG() {
        return NewAccountFragment.class.getSimpleName();
    }


    @OnClick(R.id.signInText)
    public void onSignInTextClicked() {
        navigationPresenter.replaceFragment(LoginFragment.newInstance());
    }

    @OnClick(R.id.newAccountSubmitBtn)
    public void onSubmitBtnClicked() {
        navigationPresenter.replaceFragment(SubmitAccountFragment.newInstance());
    }
}
