package brain_socket.com.dekaneh.fragment;


import android.view.View;

import brain_socket.com.dekaneh.R;

public class ForgotPasswordFragment extends BaseFragment {


    public ForgotPasswordFragment() { }

    public static ForgotPasswordFragment newInstance(){
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }

    @Override
    public void init(View rootView) {

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_forgot_password;
    }
}
