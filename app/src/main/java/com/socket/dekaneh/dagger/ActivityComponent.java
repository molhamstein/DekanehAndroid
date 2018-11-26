package com.socket.dekaneh.dagger;


import com.socket.dekaneh.activity.NotificationsActivity;
import com.socket.dekaneh.activity.rating.RatingActivity;
import com.socket.dekaneh.activity.splash.SplashActivity;
import com.socket.dekaneh.activity.cart.CartActivity;
import com.socket.dekaneh.activity.category_details.CategoryDetailsActivity;
import com.socket.dekaneh.activity.main.MainActivity;
import com.socket.dekaneh.activity.manufacturer.ManufacturerActivity;
import com.socket.dekaneh.activity.order_details.OrderDetailsActivity;
import com.socket.dekaneh.activity.product_details.ProductDetailsActivity;
import com.socket.dekaneh.activity.registration.RegistrationActivity;
import com.socket.dekaneh.activity.settings.SettingsActivity;
import com.socket.dekaneh.fragment.profile.ProfileFragment;
import com.socket.dekaneh.fragment.categories.CategoriesFragment;
import com.socket.dekaneh.fragment.main.MainFragment;
import com.socket.dekaneh.fragment.offers.OffersFragment;
import com.socket.dekaneh.fragment.registration.new_account.NewAccountFragment;
import com.socket.dekaneh.fragment.registration.login.LoginFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RegistrationActivity activity);

    void inject(MainActivity activity);

    void inject(ProductDetailsActivity activity);

    void inject(CartActivity activity);

    void inject(CategoryDetailsActivity activity);

    void inject(SettingsActivity activity);

    void inject(ManufacturerActivity activity);

    void inject(OrderDetailsActivity activity);

    void inject(NotificationsActivity activity);

    void inject(SplashActivity activity);

    void inject(RatingActivity activity);

    void inject(LoginFragment fragment);

    void inject(NewAccountFragment fragment);

    void inject(MainFragment fragment);

    void inject(OffersFragment fragment);

    void inject(CategoriesFragment fragment);

    void inject(ProfileFragment fragment);

}
