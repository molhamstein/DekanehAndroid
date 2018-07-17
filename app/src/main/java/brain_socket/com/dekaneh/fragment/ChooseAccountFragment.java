package brain_socket.com.dekaneh.fragment;


import android.view.View;

import brain_socket.com.dekaneh.R;
import butterknife.OnClick;

public class ChooseAccountFragment extends BaseFragment {

    public ChooseAccountFragment() { }

    public static ChooseAccountFragment newInstance() {
        ChooseAccountFragment fragment = new ChooseAccountFragment();
        return fragment;
    }

    @Override
    public void init(View rootView) {

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_choose_account;
    }

    @Override
    public String TAG() {
        return ChooseAccountFragment.class.getSimpleName();
    }


    @OnClick(R.id.account_1)
    public void onCardOneClicked(){
        navigationPresenter.replaceFragment(NewAccountFragment.newInstance());
    }

    @OnClick(R.id.account_2)
    public void onCardTwoClicked(){
        navigationPresenter.replaceFragment(NewAccountFragment.newInstance());
    }
}
