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
import brain_socket.com.dekaneh.adapter.HomeCategoriesAdapter;
import brain_socket.com.dekaneh.adapter.SubCategoriesAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.ProductsSection;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDetailsActivity extends BaseActivity {

    @BindView(R.id.catDetailsToolbar)
    Toolbar toolbar;
    @BindView(R.id.catDetailsSubCatRV)
    RecyclerView subcategoriesRV;
    @BindView(R.id.catDetailsProductsRV)
    RecyclerView productsRV;

    public static void start(Context context) {
        Intent starter = new Intent(context, CategoryDetailsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<String> titles = new ArrayList<>();
        titles.add("All");
        titles.add("Chips");
        titles.add("Biscuits");
        titles.add("Beverages");
        titles.add("Pastry");
        subcategoriesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        subcategoriesRV.setAdapter(new SubCategoriesAdapter(titles));


//        List<Product> products = new ArrayList<>();
//        products.add(new Product("200", true));
//        products.add(new Product("350"));
//        products.add(new Product("2500"));
//        products.add(new Product("1200"));
//        products.add(new Product("1500"));
//        List<ProductsSection> sections = new ArrayList<>();
//        sections.add(new ProductsSection("منتجات مختارة", products));
//        sections.add(new ProductsSection("منظفات", products));
//        HomeCategoriesAdapter adapter = new HomeCategoriesAdapter(sections);
//        productsRV.setAdapter(adapter);
//        productsRV.setLayoutManager(new LinearLayoutManager(this));
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
