package brain_socket.com.dekaneh.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.cart.CartActivity;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.fragment.categories.CategoriesFragment;
import brain_socket.com.dekaneh.fragment.main.MainFragment;
import brain_socket.com.dekaneh.fragment.offers.OffersFragment;
import brain_socket.com.dekaneh.fragment.ProfileFragment;
import brain_socket.com.dekaneh.network.PicassoImageLoadingService;
import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.Slider;

public class MainActivity extends BaseActivity implements MainActivityVP.View {


    @Inject
    MainActivityVP.Presenter<MainActivityVP.View> presenter;

    @BindView(R.id.mainToolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationViewEx bottomNavigation;
    @BindView(R.id.mainToolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.mainToolbarLogo)
    View toolbarLogo;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getActivityComponent().inject(this);
        presenter.onAttach(this);

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.enableAnimation(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.enableShiftingMode(false);
        Slider.init(new PicassoImageLoadingService());
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
    public void showToolbarCanopy(boolean show) {
        if (show) toolbar.setBackgroundResource(R.drawable.canopy_up);
        else toolbar.setBackgroundResource(R.drawable.splash_background);
    }


    private void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, fragment, fragment.TAG())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivityForResult(new Intent(this, CartActivity.class), 1);
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
}
