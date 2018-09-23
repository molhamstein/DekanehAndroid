package brain_socket.com.dekaneh.activity.category_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.ManufacturersAdapter;
import brain_socket.com.dekaneh.adapter.SubCategoriesAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.SubCategory;
import brain_socket.com.dekaneh.utils.GsonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDetailsActivity extends BaseActivity implements CategoryDetailsVP.View {

    @Inject
    CategoryDetailsVP.Presenter<CategoryDetailsVP.View> presenter;
    @Inject
    ManufacturersAdapter manufacturersAdapter;
    @Inject
    SubCategoriesAdapter subCategoriesAdapter;

    @BindView(R.id.catDetailsToolbar)
    Toolbar toolbar;
    @BindView(R.id.catDetailsSubCatRV)
    RecyclerView subcategoriesRV;
    @BindView(R.id.catDetailsProductsRV)
    RecyclerView productsRV;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public static void start(Context context, Category category) {
        Intent starter = new Intent(context, CategoryDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Category.TAG, GsonUtils.convertObjectToJson(category));
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        ButterKnife.bind(this);

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        presenter.onAttach(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        subcategoriesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        subcategoriesRV.setAdapter(subCategoriesAdapter);
        productsRV.setLayoutManager(new LinearLayoutManager(this));
        productsRV.setAdapter(manufacturersAdapter);

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
    public void addAllManufacturers(List<Manufacturer> manufacturers) {
        manufacturersAdapter.addAllManufacturers(manufacturers);
    }

    @Override
    public void addAllSubCategories(List<SubCategory> subCategories) {
        subCategoriesAdapter.addAllSubCategories(subCategories);
    }

    @Override
    public void setTitle(String title) {
        toolbarTitle.setText(title);
    }
}
