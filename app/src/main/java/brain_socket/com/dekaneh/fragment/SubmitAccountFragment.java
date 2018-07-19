package brain_socket.com.dekaneh.fragment;


import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;

public class SubmitAccountFragment extends BaseFragment {


    public SubmitAccountFragment() { }


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

}
