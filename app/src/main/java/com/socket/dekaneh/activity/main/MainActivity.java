package com.socket.dekaneh.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.NotificationsActivity;
import com.socket.dekaneh.activity.cart.CartActivity;
import com.socket.dekaneh.adapter.OnItemCountChange;
import com.socket.dekaneh.adapter.SearchAdapter;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.fragment.categories.CategoriesFragment;
import com.socket.dekaneh.fragment.main.MainFragment;
import com.socket.dekaneh.fragment.offers.OffersFragment;
import com.socket.dekaneh.fragment.profile.ProfileFragment;
import com.socket.dekaneh.network.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityVP.View {


    @Inject
    MainActivityVP.Presenter<MainActivityVP.View> presenter;
    @Inject
    SearchAdapter searchAdapter;

    @BindView(R.id.mainToolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationViewEx bottomNavigation;
    @BindView(R.id.mainToolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.mainToolbarLogo)
    View toolbarLogo;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.searchLayout)
    View searchLayout;
    @BindView(R.id.searchRV)
    RecyclerView searchRV;
    @BindView(R.id.cartItemsCount)
    TextView cartItemsCount;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    presenter.onBottomNavMainItemClick();
                    return true;
                case R.id.navigation_categories:
                    presenter.onBottomCategoriesItemClick();
                    return true;
                case R.id.navigation_offers:
                    presenter.onBottomOffersItemClick();
                    return true;
                case R.id.navigation_profile:
                    presenter.onBottomProfileItemClick();
                    return true;
            }
            return false;
        }
    };


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateCartItemsCountText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        presenter.checkUserActivated();
        presenter.updateFirebaseToken();

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.enableShiftingMode(false);

        searchRV.setLayoutManager(new GridLayoutManager(this, 2));
        searchRV.setAdapter(searchAdapter);
        searchAdapter.setOnItemCountChange(new OnItemCountChange() {
            @Override
            public void onChange() {
                presenter.updateCartItemsCountText();
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                searchLayout.setVisibility(View.GONE);
                searchAdapter.clear();
            }
        });


    }

    @Override
    public void showMainFragment() {
        replaceFragment(MainFragment.newInstance());
    }

    @Override
    public void showOffersFragment() {
        replaceFragment(OffersFragment.newInstance());
    }

    @Override
    public void showCategoriesFragment() {
        replaceFragment(CategoriesFragment.newInstance());
    }

    @Override
    public void showProfileFragment() {
        replaceFragment(ProfileFragment.newInstance());
    }

    @Override
    public void showToolbarTitle(boolean show) {
        if (show) toolbarTitle.setVisibility(View.VISIBLE);
        else toolbarTitle.setVisibility(View.GONE);
    }

    @Override
    public void showToolbarLogo(boolean show) {
        if (show) toolbarLogo.setVisibility(View.VISIBLE);
        else toolbarLogo.setVisibility(View.GONE);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void setToolbarTitle(int title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void updateCartItemCount(String count) {

        cartItemsCount.setText(count);

    }

    @Override
    public void updateSearchView(List<Product> products) {
        searchAdapter.addAllProducts(products);
    }

    private void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, fragment, fragment.TAG())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivityForResult(new Intent(this, CartActivity.class), 1);
                return false;
            case R.id.action_notifications:
                NotificationsActivity.start(this);
                return false;
                case R.id.action_favorite:
//                NotificationsActivity.start(this);
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                presenter.restart();
            }
        }
    }

    public void navigateToOffersFragment() {
        bottomNavigation.setCurrentItem(2);
    }
}
