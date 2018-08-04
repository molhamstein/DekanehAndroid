package brain_socket.com.dekaneh.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.MainSliderAdapter;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.adapter.SectionedProductsAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.dagger.Horizontal;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.ProductsSection;
import butterknife.BindView;
import ss.com.bannerslider.Slider;

public class MainFragment extends BaseFragment {

    @Inject
    OffersAdapter offersAdapter;
    @Inject
    @Horizontal
    LinearLayoutManager linearLayoutManager;

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

        offersRV.setLayoutManager(linearLayoutManager);
        offersRV.setAdapter(offersAdapter);
        productsRV.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        List<Product> products = new ArrayList<>();
        products.add(new Product("200", true));
        products.add(new Product("350"));
        products.add(new Product("2500"));
        products.add(new Product("1200"));
        products.add(new Product("1500"));
        List<ProductsSection> sections = new ArrayList<>();
        sections.add(new ProductsSection("منتجات مختارة", products));
        sections.add(new ProductsSection("منظفات", products));
        SectionedProductsAdapter adapter = new SectionedProductsAdapter(sections);
        productsRV.setAdapter(adapter);
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
}
