package brain_socket.com.dekaneh.dagger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.cart.CartActivityPresenter;
import brain_socket.com.dekaneh.activity.cart.CartActivityVP;
import brain_socket.com.dekaneh.activity.main.MainActivityPresenter;
import brain_socket.com.dekaneh.activity.main.MainActivityVP;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsPresenter;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsVP;
import brain_socket.com.dekaneh.activity.registration.RegistrationActivityPresenter;
import brain_socket.com.dekaneh.activity.registration.RegistrationActivityVP;
import brain_socket.com.dekaneh.adapter.CartOrdersAdapter;
import brain_socket.com.dekaneh.adapter.CategoriesAdapter;
import brain_socket.com.dekaneh.adapter.HomeCategoriesAdapter;
import brain_socket.com.dekaneh.adapter.OffersAdapter;
import brain_socket.com.dekaneh.application.AppSchedulerProvider;
import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.fragment.categories.CategoriesFragmentPresenter;
import brain_socket.com.dekaneh.fragment.categories.CategoriesFragmentVP;
import brain_socket.com.dekaneh.fragment.main.MainFragmentPresenter;
import brain_socket.com.dekaneh.fragment.main.MainFragmentVP;
import brain_socket.com.dekaneh.fragment.offers.OffersFragmentPresenter;
import brain_socket.com.dekaneh.fragment.offers.OffersFragmentVP;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragmentPresenter;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragmentVP;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.Session;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }

    @Provides
    AppCompatActivity providesActivity(){
        return activity;
    }

    @Provides
    Session provideSession(AppCompatActivity context) {
        return new Session(context);
    }

    @Provides
    CacheStore provideCacheStore(AppCompatActivity context, Session session) {
        return new CacheStore(context, session);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    RegistrationActivityPresenter<RegistrationActivityVP.View> provideRegistrationActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        return new RegistrationActivityPresenter<>(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Provides
    LoginFragmentVP.Presenter<LoginFragmentVP.View> provideLoginPresenter(LoginFragmentPresenter<LoginFragmentVP.View> presenter) {
        return presenter;
    }

    @Provides
    MainActivityVP.Presenter<MainActivityVP.View> provideMainActivityPresenter(MainActivityPresenter<MainActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    ProductDetailsVP.Presenter<ProductDetailsVP.View> provideProductDetailsPresenter(ProductDetailsPresenter<ProductDetailsVP.View> presenter) {
        return presenter;
    }

    @Provides
    MainFragmentVP.Presenter<MainFragmentVP.View> provideMainFragmentPresenter(MainFragmentPresenter<MainFragmentVP.View> presenter) {
        return presenter;
    }

    @Provides
    CartActivityVP.Presenter<CartActivityVP.View> provideCartActivityPresenter(CartActivityPresenter<CartActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    CategoriesFragmentVP.Presenter<CategoriesFragmentVP.View> provideCategoriesFragmentPresenter(CategoriesFragmentPresenter<CategoriesFragmentVP.View> presenter) {
        return presenter;
    }

    @Provides
    OffersFragmentVP.Presenter<OffersFragmentVP.View> provideOffersFragmentPresenter(OffersFragmentPresenter<OffersFragmentVP.View> presenter) {
        return presenter;
    }

    @Provides
    HomeCategoriesAdapter providesHomeCategoriesAdapter() {
        return new HomeCategoriesAdapter(new ArrayList<HomeCategory>());
    }

    @FragmentMain
    @Provides
    OffersAdapter providesMainOffersAdapter(AppCompatActivity context) {
        return new OffersAdapter(R.layout.item_offer, context);
    }

    @Provides
    OffersAdapter providesOffersAdapter(AppCompatActivity context) {
        return new OffersAdapter(R.layout.item_offer_fragment_offers, context);
    }

    @Provides
    CategoriesAdapter provideCategoriesAdapter() {
        return new CategoriesAdapter();
    }

    @Provides
    CartOrdersAdapter provideCartAdatper(AppCompatActivity context) {
        return new CartOrdersAdapter(context);
    }

    @Horizontal
    @Provides
    LinearLayoutManager provideHorizontalLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
