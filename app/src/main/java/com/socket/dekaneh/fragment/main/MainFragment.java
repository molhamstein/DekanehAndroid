package com.socket.dekaneh.fragment.main;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.main.MainActivity;
import com.socket.dekaneh.adapter.HomeCategoriesAdapter;
import com.socket.dekaneh.adapter.OffersAdapter;
import com.socket.dekaneh.adapter.OnItemCountChange;
import com.socket.dekaneh.adapter.SliderPagerAdapter;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.dagger.FragmentMain;
import com.socket.dekaneh.dagger.Horizontal;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.SliderImage;
import butterknife.BindView;
import butterknife.OnClick;
import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;

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

    @BindView(R.id.mainOffersRV)
    RecyclerView offersRV;
    @BindView(R.id.mainProductRV)
    RecyclerView productsRV;
    @BindView(R.id.mainSlider)
    AutoScrollViewPager mainPager;

    SliderPagerAdapter sliderPagerAdapter;


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
        presenter.fetchSliderImages();

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


        offersAdapter.setOnItemCountChange(new OnItemCountChange() {
            @Override
            public void onChange() {
                presenter.updateCartItemsCountText();
            }
        });

        categoriesAdapter.setOnItemCountChange(new OnItemCountChange() {
            @Override
            public void onChange() {
                presenter.updateCartItemsCountText();
            }
        });

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

    @Override
    public void addSliderImages(List<SliderImage> images) {
        mainPager.setAdapter(new SliderPagerAdapter(images));
        mainPager.startAutoScroll(4000);
        mainPager.setScrollFactor(2);
    }

    @Override
    public void addFeaturedProducts(List<Product> products) {
        categoriesAdapter.addFeaturedProducts(products, getContext());
    }

    @OnClick(R.id.seeAllOffersText)
    public void onSeeAllOffersText() {
        ((MainActivity) getActivity()).navigateToOffersFragment();
    }

}
