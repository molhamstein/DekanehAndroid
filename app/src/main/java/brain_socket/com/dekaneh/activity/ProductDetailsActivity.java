package brain_socket.com.dekaneh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.MiniOfferAdapter;
import brain_socket.com.dekaneh.adapter.ProductsAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends BaseActivity {

    @BindView(R.id.prodDetailsToolbar)
    Toolbar toolbar;
    @BindView(R.id.miniOffersRV)
    RecyclerView miniOffersRV;
    @BindView(R.id.similarProductsRV)
    RecyclerView similarProductsRV;

    public static void start(Context context) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        miniOffersRV.setLayoutManager(new LinearLayoutManager(this));
        miniOffersRV.setAdapter(new MiniOfferAdapter());
        List<Product> products = new ArrayList<>();
        products.add(new Product("200"));
        products.add(new Product("240"));
        similarProductsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        similarProductsRV.setAdapter(new ProductsAdapter(products));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
