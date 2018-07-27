package brain_socket.com.dekaneh.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import butterknife.BindView;

public class OffersFragment extends BaseFragment {

    @Inject
    OffersAdapter offersAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.offersRV)
    RecyclerView offersRV;

    public OffersFragment() {
        // Required empty public constructor
    }

    public static OffersFragment newInstance() {
        OffersFragment fragment = new OffersFragment();
        return fragment;
    }

    @Override
    public void init(View rootView) {
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        offersRV.setLayoutManager(linearLayoutManager);
        offersRV.setAdapter(offersAdapter);


    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_offers;
    }

    @Override
    public String TAG() {
        return OffersFragment.class.getSimpleName();
    }
}
