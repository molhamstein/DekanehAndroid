package brain_socket.com.dekaneh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.fragment.MainFragment;
import brain_socket.com.dekaneh.fragment.OffersFragment;
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    presenter.onBottomNavMainItemClick();
                    return true;
                case R.id.navigation_categories:
                    presenter.onBottomOffersItemClick();
                    return true;
                case R.id.navigation_offers:
                    return true;
                case R.id.navigation_profile:
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
}
