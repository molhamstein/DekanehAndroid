package brain_socket.com.dekaneh.dagger;

import javax.inject.Singleton;

import brain_socket.com.dekaneh.activity.main.MainActivity;
import brain_socket.com.dekaneh.activity.registration.RegistrationActivity;
import brain_socket.com.dekaneh.fragment.main.MainFragment;
import brain_socket.com.dekaneh.fragment.OffersFragment;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RegistrationActivity activity);

    void inject(MainActivity activity);

    void inject(LoginFragment fragment);

    void inject(MainFragment fragment);

    void inject(OffersFragment fragment);

}
