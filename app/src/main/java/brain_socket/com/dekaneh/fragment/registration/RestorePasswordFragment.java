package brain_socket.com.dekaneh.fragment.registration;


import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;

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