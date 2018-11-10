package brain_socket.com.dekaneh.fragment.main;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.main.MainActivity;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.adapter.HomeCategoriesAdapter;
import brain_socket.com.dekaneh.adapter.MainSliderAdapter;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.adapter.OnItemCountChange;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.dagger.FragmentMain;
import brain_socket.com.dekaneh.dagger.Horizontal;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.SliderImage;
import butterknife.BindView;
import butterknife.OnClick;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

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
    @BindView(R.id.mainBanner)
    View mainBanner;

    private MainSliderAdapter sliderAdapter;

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

        mainBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ASDASD", "onClick: out");
                if (sliderAdapter.getImages().get(slider.selectedSlidePosition).getType().equals("product")) {
                    Log.d("ASDASD", "onClick: in");
                    Product product = new Product(sliderAdapter.getImages().get(slider.selectedSlidePosition).getId());
                    ProductDetailsActivity.start(getContext(), product);
                }
            }
        });

//        slider.setOnSlideClickListener(new OnSlideClickListener() {
//            @Override
//            public void onSlideClick(int position) {
//                Log.d("ASDASDASDSDASDASDASD", "onSlideClick: " + position);
//                if (sliderAdapter.getImages().get(position).getType().equals("product")) {
//                    Product product = new Product(sliderAdapter.getImages().get(position).getId());
//                    ProductDetailsActivity.start(getContext(), product);
//                }
//            }
//        });


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
        sliderAdapter = new MainSliderAdapter(images);
        slider.setAdapter(sliderAdapter);

    }

    @OnClick(R.id.seeAllOffersText)
    public void onSeeAllOffersText() {
        ((MainActivity) getActivity()).navigateToOffersFragment();
    }

}
