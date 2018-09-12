package brain_socket.com.dekaneh.fragment.main;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.HomeCategoriesAdapter;
import brain_socket.com.dekaneh.adapter.MainSliderAdapter;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.dagger.FragmentMain;
import brain_socket.com.dekaneh.dagger.Horizontal;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import ss.com.bannerslider.Slider;

public class MainFragment extends BaseFragment implements MainFragmentVP.View {

    @Inject
    MainFragmentVP.Presenter<MainFragmentVP.View> presenter;
    @Inject
    HomeCategoriesAdapter categoriesAdapter;
    @Inject
    @FragmentMain
    OffersAdapter offersAdapter;
    @Inject
    @Horizontal
    LinearLayoutManager linearLayoutManager;
    @Inject
    @Horizontal
    LinearLayoutManager offersLinearLayoutManager;

    @BindView(R.id.mainParent)
    View parent;
    @BindView(R.id.mainSlider)
    Slider slider;
    @BindView(R.id.mainOffersRV)
    RecyclerView offersRV;
    @BindView(R.id.mainProductRV)
    RecyclerView productsRV;

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
        presenter.onAttach(this);

        offersRV.setLayoutManager(linearLayoutManager);
        offersRV.setAdapter(offersAdapter);
        productsRV.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        productsRV.setAdapter(categoriesAdapter);
        productsRV.setNestedScrollingEnabled(true);

        List<Integer> drawableIds = new ArrayList<>();
        drawableIds.add(R.drawable.ad);
        drawableIds.add(R.drawable.tide);

        slider.setAdapter(new MainSliderAdapter(drawableIds));

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public String TAG() {
        return MainFragment.class.getSimpleName();
    }

    @Override
    public void addCategoriesWithProducts(List<HomeCategory> categories) {
        categoriesAdapter.addAllCategories(categories);
    }

    @Override
    public void addFeaturedOffers(List<Offer> offers) {
        offersAdapter.addAllOffers(offers);
    }
}
