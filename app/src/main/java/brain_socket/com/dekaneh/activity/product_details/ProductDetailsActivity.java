package brain_socket.com.dekaneh.activity.product_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.MiniOfferAdapter;
import brain_socket.com.dekaneh.adapter.ProductsAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.GsonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsVP.View {

    private Product product;

    @Inject
    ProductDetailsVP.Presenter<ProductDetailsVP.View> presenter;

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
        getActivityComponent().inject(this);

        presenter.onAttach(this);

        miniOffersRV.setLayoutManager(new LinearLayoutManager(this));
        miniOffersRV.setAdapter(new MiniOfferAdapter());
        List<Product> products = new ArrayList<>();
        similarProductsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        similarProductsRV.setAdapter(new ProductsAdapter(products));

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
    public void updateView(Product product) {
        this.name.setText(product.getNameAr());
        this.price.setText(String.valueOf(product.getRetailPrice()));
        this.packName.setText(product.getNameAr());
        this.manufacturer.setText(product.getManufacturer().getNameAr());
    }
}
