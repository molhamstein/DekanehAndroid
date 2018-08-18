package brain_socket.com.dekaneh.fragment;

import android.view.View;
import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;

public class ProfileFragment extends BaseFragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void init(View rootView) {

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public String TAG() {
        return this.getClass().getSimpleName();
    }
}
