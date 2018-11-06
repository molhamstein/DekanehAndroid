package brain_socket.com.dekaneh.activity.product_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.MiniOfferAdapter;
import brain_socket.com.dekaneh.adapter.ProductsAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.Session;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.GsonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsVP.View {

    private Product product;

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
    @BindView(R.id.offersView2)
    View offersView2;
    @BindView(R.id.similarView1)
    View similarView1;
    @BindView(R.id.similarView2)
    View similarView2;

    @BindView(R.id.orderCount)
    TextView orderCount;

    MiniOfferAdapter miniOfferAdapter;

    public static void start(Context context, Product product) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Product.TAG, GsonUtils.convertObjectToJson(product));
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
    public void updateView(Product product, String imageUrl, boolean isHoreca) {
        if (!imageUrl.equals(""))
            Picasso.get().load(imageUrl).into(this.productImage);
        this.name.setText(product.getNameAr());
        if (isHoreca) {
            this.price.setText(String.valueOf(product.getHorecaPrice()));
        } else {
            this.price.setText(String.valueOf(product.getWholeSalePrice()));
        }
        this.packName.setText(product.getNameAr());
        this.manufacturer.setText(product.getManufacturer().getNameAr());
        this.description.setText(product.getDescription());
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
        similarView1.setVisibility(View.GONE);
        similarView2.setVisibility(View.GONE);
    }

    @Override
    public void hideOffersSection() {
        offersView1.setVisibility(View.GONE);
        offersView2.setVisibility(View.GONE);
        miniOffersRV.setVisibility(View.GONE);
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
