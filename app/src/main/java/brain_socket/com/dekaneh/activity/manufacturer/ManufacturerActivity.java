package brain_socket.com.dekaneh.activity.manufacturer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.GsonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ManufacturerActivity extends BaseActivity implements ManufacturerActivityVP.View {

    @Inject
    ManufacturerActivityVP.Presenter<ManufacturerActivityVP.View> presenter;
    @Inject
    OffersAdapter adapter;

    @BindView(R.id.manufacturerToolbar)
    Toolbar toolbar;
    @BindView(R.id.productsRV)
    RecyclerView productsRV;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;


    public static void start(Context context, Manufacturer manufacturer) {
        Intent starter = new Intent(context, ManufacturerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Manufacturer.TAG, GsonUtils.convertObjectToJson(manufacturer));
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer);
        ButterKnife.bind(this);

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        presenter.onAttach(this);

        productsRV.setLayoutManager(new LinearLayoutManager(this));
        productsRV.setAdapter(adapter);
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
    public void addAllProducts(List<Offer> offers) {
        adapter.addAllOffers(offers);
    }

    @Override
    public void setTitle(String title) {
        toolbarTitle.setText(title);
    }
}
