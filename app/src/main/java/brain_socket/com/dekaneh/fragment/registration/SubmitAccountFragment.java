package brain_socket.com.dekaneh.fragment.registration;


import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragment;
import butterknife.OnClick;

public class SubmitAccountFragment extends BaseFragment {


    public SubmitAccountFragment() { }

    public static SubmitAccountFragment newInstance() {
        SubmitAccountFragment fragment = new SubmitAccountFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

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
        navigationPresenter.replaceFragment(LoginFragment.newInstance());
    }

}
