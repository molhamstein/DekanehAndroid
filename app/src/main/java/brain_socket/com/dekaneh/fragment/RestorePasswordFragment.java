package brain_socket.com.dekaneh.fragment;


import android.view.View;

import brain_socket.com.dekaneh.R;

public class RestorePasswordFragment extends BaseFragment {


    public RestorePasswordFragment() { }


    @Override
    public void init(View rootView) {

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_restore_password;
    }

    @Override
    public String TAG() {
        return RestorePasswordFragment.class.getSimpleName();
    }
}
