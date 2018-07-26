package brain_socket.com.dekaneh.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.custom.SyncScroll;
import butterknife.BindView;

public class MainFragment extends BaseFragment {

    @Inject
    OffersAdapter offersAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.syncScroll)
    SyncScroll syncScroll;
    @BindView(R.id.mainParent)
    View parent;
    @BindView(R.id.mainHeader)
    View header;
    @BindView(R.id.mainOffersRV)
    RecyclerView offersRV;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        syncScroll.setAnchorView(parent);
        syncScroll.setSynchronizedView(header);
        offersRV.setLayoutManager(linearLayoutManager);
        offersRV.setAdapter(offersAdapter);
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
