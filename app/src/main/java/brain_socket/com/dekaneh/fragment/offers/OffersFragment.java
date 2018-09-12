package brain_socket.com.dekaneh.fragment.offers;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.network.model.Offer;
import butterknife.BindView;

public class OffersFragment extends BaseFragment implements OffersFragmentVP.View{

    @Inject
    OffersAdapter offersAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    OffersFragmentVP.Presenter<OffersFragmentVP.View> presenter;

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
        presenter.onAttach(this);
        presenter.fetchOffers();

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

    @Override
    public void addOffers(List<Offer> offers) {
        offersAdapter.addAllOffers(offers);
    }
}
