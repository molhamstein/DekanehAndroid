package com.socket.dekaneh.activity.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.GridProductsAdapter;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.network.model.Product;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends BaseActivity implements FavoriteActivityVP.View {


    @Inject
    GridProductsAdapter adapter;
    @Inject
    FavoriteActivityVP.Presenter<FavoriteActivityVP.View> presenter;

    @BindView(R.id.favoriteProductsRV)
    RecyclerView favoriteProductsRV;
    @BindView(R.id.favoriteToolbar)
    Toolbar toolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, FavoriteActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onResume() {
        presenter.fetchProducts();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        if (getActivityComponent() != null) {
            getActivityComponent().inject(this);
        }
        presenter.onAttach(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        favoriteProductsRV.setLayoutManager(new GridLayoutManager(this, 2));
        favoriteProductsRV.setAdapter(adapter);
        presenter.fetchProducts();
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


    @Override
    public void addAllProducts(List<Product> products) {
        adapter.addAllProducts(products);
    }
}
