package brain_socket.com.dekaneh.fragment.registration;


import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.custom.DekanehInterpolator;
import brain_socket.com.dekaneh.fragment.registration.new_account.NewAccountFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class ChooseAccountFragment extends BaseFragment {


    @BindView(R.id.account_1Card)
    View account1;
    @BindView(R.id.account_2Card)
    View account2;

    public ChooseAccountFragment() { }

    public static ChooseAccountFragment newInstance() {
        ChooseAccountFragment fragment = new ChooseAccountFragment();
        return fragment;
    }

    @Override
    public void init(View rootView) {

        ViewAnimator.animate(account1).translationX(-800, 0)
                .alpha(0, 1).duration(800)
                .andAnimate(account2).translationX(-700, 0)
                .alpha(0, 1).duration(800)
                .interpolator(new DekanehInterpolator(1f))
                .decelerate().start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_choose_account;
    }

    @Override
    public String TAG() {
        return ChooseAccountFragment.class.getSimpleName();
    }


    @OnClick(R.id.account_1Card)
    public void onCardOneClicked(){
        navigationPresenter.replaceFragment(NewAccountFragment.newInstance());
    }

    @OnClick(R.id.account_2Card)
    public void onCardTwoClicked(){
        navigationPresenter.replaceFragment(NewAccountFragment.newInstance());
    }


    private void performOutAnimation() {
    }
}
