package com.socket.dekaneh.fragment.offers;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.OffersAdapter;
import com.socket.dekaneh.adapter.OnItemCountChange;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.network.model.Offer;
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

        offersAdapter.setOnItemCountChange(new OnItemCountChange() {
            @Override
            public void onChange() {
                presenter.updateCartItemsCountText();
            }
        });

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

    @Override
    public void onResume() {
        if (offersAdapter != null)
            offersAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
