package brain_socket.com.dekaneh.fragment;


import android.view.View;

import brain_socket.com.dekaneh.R;
import butterknife.OnClick;

public class NewAccountFragment extends BaseFragment {

    public NewAccountFragment() { }

    public static NewAccountFragment newInstance() {
        NewAccountFragment fragment = new NewAccountFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

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
    public void onSignInTextClicked(){
        navigationPresenter.replaceFragment(LoginFragment.newInstance());
    }
}
