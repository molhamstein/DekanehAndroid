package brain_socket.com.dekaneh.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.custom.SyncScroll;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    @BindView(R.id.syncScroll)
    SyncScroll syncScroll;
    @BindView(R.id.test)
    View view1;
    @BindView(R.id.test1)
    View view2;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        syncScroll.setAnchorView(view1);
        syncScroll.setSynchronizedView(view2);

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public String TAG() {
        return MainFragment.class.getSimpleName();
    }
}
