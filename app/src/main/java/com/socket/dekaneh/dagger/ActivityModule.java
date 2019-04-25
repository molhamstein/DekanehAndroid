package com.socket.dekaneh.dagger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.cart.CartActivityPresenter;
import com.socket.dekaneh.activity.favorite.FavoriteActivityPresenter;
import com.socket.dekaneh.activity.favorite.FavoriteActivityVP;
import com.socket.dekaneh.activity.featured_products.FeaturedActivityPresenter;
import com.socket.dekaneh.activity.featured_products.FeaturedActivityVP;
import com.socket.dekaneh.activity.rating.RatingActivityPesenter;
import com.socket.dekaneh.activity.rating.RatingActivityVP;
import com.socket.dekaneh.activity.splash.SplashActivityPresenter;
import com.socket.dekaneh.activity.splash.SplashActivityVP;
import com.socket.dekaneh.activity.category_details.CategoryDetailsPresenter;
import com.socket.dekaneh.activity.category_details.CategoryDetailsVP;
import com.socket.dekaneh.activity.main.MainActivityPresenter;
import com.socket.dekaneh.activity.main.MainActivityVP;
import com.socket.dekaneh.activity.manufacturer.ManufacturerActivityPresenter;
import com.socket.dekaneh.activity.manufacturer.ManufacturerActivityVP;
import com.socket.dekaneh.activity.order_details.OrderDetailsPresenter;
import com.socket.dekaneh.activity.order_details.OrderDetailsVP;
import com.socket.dekaneh.activity.product_details.ProductDetailsPresenter;
import com.socket.dekaneh.activity.product_details.ProductDetailsVP;
import com.socket.dekaneh.activity.registration.RegistrationActivityPresenter;
import com.socket.dekaneh.activity.registration.RegistrationActivityVP;
import com.socket.dekaneh.activity.settings.SettingsActivityPresenter;
import com.socket.dekaneh.activity.settings.SettingsActivityVP;
import com.socket.dekaneh.adapter.CartOrdersAdapter;
import com.socket.dekaneh.adapter.CategoriesAdapter;
import com.socket.dekaneh.adapter.GridProductsAdapter;
import com.socket.dekaneh.adapter.HomeCategoriesAdapter;
import com.socket.dekaneh.adapter.ManufacturersAdapter;
import com.socket.dekaneh.adapter.MiniOfferAdapter;
import com.socket.dekaneh.adapter.OffersAdapter;
import com.socket.dekaneh.adapter.OrderDetailsItemsAdapter;
import com.socket.dekaneh.adapter.OrdersAdapter;
import com.socket.dekaneh.adapter.ProductsAdapter;
import com.socket.dekaneh.adapter.SearchAdapter;
import com.socket.dekaneh.adapter.SubCategoriesAdapter;
import com.socket.dekaneh.application.AppSchedulerProvider;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.fragment.categories.CategoriesFragmentPresenter;
import com.socket.dekaneh.fragment.categories.CategoriesFragmentVP;
import com.socket.dekaneh.fragment.main.MainFragmentPresenter;
import com.socket.dekaneh.fragment.main.MainFragmentVP;
import com.socket.dekaneh.fragment.offers.OffersFragmentPresenter;
import com.socket.dekaneh.fragment.offers.OffersFragmentVP;
import com.socket.dekaneh.fragment.profile.ProfileFragmentPresenter;
import com.socket.dekaneh.fragment.profile.ProfileFragmentVP;
import com.socket.dekaneh.fragment.registration.forgetPassword.ForgetPasswordPresenter;
import com.socket.dekaneh.fragment.registration.forgetPassword.ForgetPasswordVP;
import com.socket.dekaneh.fragment.registration.login.LoginFragmentPresenter;
import com.socket.dekaneh.fragment.registration.login.LoginFragmentVP;
import com.socket.dekaneh.fragment.registration.new_account.NewAccountFragmentPresenter;
import com.socket.dekaneh.fragment.registration.new_account.NewAccountFragmentVP;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.Session;

import brain_socket.com.dekaneh.activity.cart.CartActivityVP;

import com.socket.dekaneh.adapter.CouponsAdapter;

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
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity providesActivity() {
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
    CompositeDisposable provideCompositeDisposable() {
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
    ForgetPasswordVP.Presenter<ForgetPasswordVP.View> provideForgetPasswordPresenter(ForgetPasswordPresenter<ForgetPasswordVP.View> presenter) {
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
    CategoryDetailsVP.Presenter<CategoryDetailsVP.View> provideCategoryDetailsPresenter(CategoryDetailsPresenter<CategoryDetailsVP.View> presenter) {
        return presenter;
    }

    @Provides
    SettingsActivityVP.Presenter<SettingsActivityVP.View> providesSettingsActivityPresenter(SettingsActivityPresenter<SettingsActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    OrderDetailsVP.Presenter<OrderDetailsVP.View> provideOrderDetailsPresenter(OrderDetailsPresenter<OrderDetailsVP.View> presenter) {
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
    ProfileFragmentVP.Presenter<ProfileFragmentVP.View> provideProfileFragmentPresenter(ProfileFragmentPresenter<ProfileFragmentVP.View> presenter) {
        return presenter;
    }

    @Provides
    ManufacturerActivityVP.Presenter<ManufacturerActivityVP.View> provideManufacturerActivityPresenter(ManufacturerActivityPresenter<ManufacturerActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    NewAccountFragmentVP.Presenter<NewAccountFragmentVP.View> provideNewAccountFragmentPresenter(NewAccountFragmentPresenter<NewAccountFragmentVP.View> presenter) {
        return presenter;
    }

    @Provides
    SplashActivityVP.Presenter<SplashActivityVP.View> provideSplashActivityPresenter(SplashActivityPresenter<SplashActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    RatingActivityVP.Presenter<RatingActivityVP.View> provideRatingActivityPresenter(RatingActivityPesenter<RatingActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    FavoriteActivityVP.Presenter<FavoriteActivityVP.View> provideFavoriteActivityPresenter(FavoriteActivityPresenter<FavoriteActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    FeaturedActivityVP.Presenter<FeaturedActivityVP.View> provideFeaturedActivityPresenter(FeaturedActivityPresenter<FeaturedActivityVP.View> presenter) {
        return presenter;
    }

    @Provides
    HomeCategoriesAdapter providesHomeCategoriesAdapter(AppCompatActivity context) {
        return new HomeCategoriesAdapter(context);
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

    @Provides
    OrderDetailsItemsAdapter provideOrderDetailsAdatper(AppCompatActivity context) {
        return new OrderDetailsItemsAdapter(context);
    }

    @Provides
    OrdersAdapter provideOrdersAdapter(AppCompatActivity context) {
        return new OrdersAdapter(context);
    }

    @Provides
    ManufacturersAdapter provideManufacturersAdapter(AppCompatActivity context) {
        return new ManufacturersAdapter(context);
    }

    @Provides
    SubCategoriesAdapter provideSubCategoriesAdapter() {
        return new SubCategoriesAdapter();
    }

    @Provides
    SearchAdapter providesSearchAdapter(CacheStore cacheStore) {
        return new SearchAdapter(cacheStore);
    }

    @Provides
    ProductsAdapter provideProductsAdapter(CacheStore cacheStore) {
        return new ProductsAdapter(cacheStore, true);
    }

    @ProductDetails
    @Provides
    ProductsAdapter provideProductsAdapterWithoutSeeMore(CacheStore cacheStore) {
        return new ProductsAdapter(cacheStore, false);
    }

    @Provides
    MiniOfferAdapter provideMiniOffersAdapterWithoutSeeMore(CacheStore cacheStore) {
        return new MiniOfferAdapter(cacheStore);
    }

    @Provides
    GridProductsAdapter provideManufacturerGridProductsAdapter(CacheStore cacheStore) {
        return new GridProductsAdapter(cacheStore);
    }

    @Provides
    CouponsAdapter provideCouponsAdapter() {
        return new CouponsAdapter();
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
