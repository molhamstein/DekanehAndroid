package com.socket.dekaneh.activity.product_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.socket.dekaneh.activity.manufacturer.ManufacturerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.MiniOfferAdapter;
import com.socket.dekaneh.adapter.ProductsAdapter;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsVP.View {


    @Inject
    ProductDetailsVP.Presenter<ProductDetailsVP.View> presenter;
    @Inject
    ProductsAdapter productsAdapter;

    @BindView(R.id.prodDetailsToolbar)
    Toolbar toolbar;
    @BindView(R.id.miniOffersRV)
    RecyclerView miniOffersRV;
    @BindView(R.id.similarProductsRV)
    RecyclerView similarProductsRV;

    @BindView(R.id.productName)
    TextView name;
    @BindView(R.id.productPrice)
    TextView price;
    @BindView(R.id.productPackName)
    TextView packName;
    @BindView(R.id.productManufacturer)
    TextView manufacturer;
    @BindView(R.id.productImage)
    ImageView productImage;
    @BindView(R.id.productDescription)
    TextView description;
    @BindView(R.id.similarProductsLayout)
    View similarProductsLayout;
    @BindView(R.id.offersView1)
    View offersView1;
    @BindView(R.id.productOfficialPrice)
    TextView productOfficialPrice;
    @BindView(R.id.productDescriptionTitle)
    View productDescriptionTitle;
    @BindView(R.id.percent)
    TextView percentage;
    @BindView(R.id.fav)
    ImageView btnFav;

    @BindView(R.id.orderCount)
    TextView orderCount;

    MiniOfferAdapter miniOfferAdapter;

    public static void start(Context context, Product product) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Product.TAG, GsonUtils.convertObjectToJson(product));
        bundle.putBoolean("isOffer", false);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    public static void startAsOffer(Context context, Product product, String percentage) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Product.TAG, GsonUtils.convertObjectToJson(product));
        bundle.putBoolean("isOffer", true);
        bundle.putString("percentage", percentage);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        getActivityComponent().inject(this);

        presenter.onAttach(this);
        miniOfferAdapter = new MiniOfferAdapter();

        miniOffersRV.setLayoutManager(new LinearLayoutManager(this));
        miniOffersRV.setAdapter(miniOfferAdapter);
        similarProductsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        similarProductsRV.setAdapter(productsAdapter);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateView(final Product product, String imageUrl, boolean isHoreca) {
        if (!imageUrl.equals(""))
            Picasso.get().load(imageUrl).into(this.productImage);
        this.name.setText(product.getNameAr());
        if (isHoreca) {
            this.price.setText(String.valueOf(product.getHorecaPrice()));
        } else {
            this.price.setText(String.valueOf(product.getWholeSalePrice()));
        }
        this.productOfficialPrice.setText(String.valueOf(product.getMarketOfficialPrice()));
        this.packName.setText(product.getPack());
        this.manufacturer.setText(product.getManufacturer().getNameAr());
        if (product.getDescription().isEmpty() || product.getDescription().equals("")) {
            this.productDescriptionTitle.setVisibility(View.GONE);
            this.description.setVisibility(View.GONE);
        } else this.description.setText(product.getDescription());

        this.manufacturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManufacturerActivity.start(view.getContext(), product.getManufacturer());
            }
        });

        if (getIntent().getBooleanExtra("isOffer", false)) {
            percentage.setVisibility(View.VISIBLE);
            percentage.setText(getIntent().getStringExtra("percentage"));
        }
    }

    @Override
    public void addAllSimilarProducts(List<Product> products) {
        productsAdapter.addAllProducts(products);
    }

    @Override
    public void addAllOffers(List<Offer> offers) {
        miniOfferAdapter.addAllOffers(offers);
    }

    @Override
    public void updateOrderCountText(int count) {
        orderCount.setText(String.valueOf(count));
    }

    @Override
    public void hideSimilarProductsSection() {
        similarProductsLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideOffersSection() {
        offersView1.setVisibility(View.GONE);
        miniOffersRV.setVisibility(View.GONE);
    }

    @Override
    public void setFavorite(boolean isFav) {
        if (isFav) btnFav.setImageResource(R.drawable.star_active);
        else btnFav.setImageResource(R.drawable.star);

    }

    @OnClick(R.id.fav)
    public void onFavBtnClicked() {
        presenter.favorite();
    }

    @OnClick(R.id.plusOne)
    public void onPlusOneBtnClicked() {
        presenter.onPlusBtnClicked();
    }

    @OnClick(R.id.minusOne)
    public void onMinusOneBtnClicked() {
        presenter.onMinusBtnClicked();
    }

}
