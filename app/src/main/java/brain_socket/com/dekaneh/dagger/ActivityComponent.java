package brain_socket.com.dekaneh.dagger;


import brain_socket.com.dekaneh.activity.cart.CartActivity;
import brain_socket.com.dekaneh.activity.category_details.CategoryDetailsActivity;
import brain_socket.com.dekaneh.activity.main.MainActivity;
import brain_socket.com.dekaneh.activity.manufacturer.ManufacturerActivity;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.activity.registration.RegistrationActivity;
import brain_socket.com.dekaneh.activity.settings.SettingsActivity;
import brain_socket.com.dekaneh.fragment.profile.ProfileFragment;
import brain_socket.com.dekaneh.fragment.categories.CategoriesFragment;
import brain_socket.com.dekaneh.fragment.main.MainFragment;
import brain_socket.com.dekaneh.fragment.offers.OffersFragment;
import brain_socket.com.dekaneh.fragment.registration.NewAccountFragment;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragment;
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

    void inject(LoginFragment fragment);

    void inject(NewAccountFragment fragment);

    void inject(MainFragment fragment);

    void inject(OffersFragment fragment);

    void inject(CategoriesFragment fragment);

    void inject(ProfileFragment fragment);

}
