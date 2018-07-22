package brain_socket.com.dekaneh.dagger;

import brain_socket.com.dekaneh.activity.registration.RegistrationActivity;
import brain_socket.com.dekaneh.fragment.login.LoginFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RegistrationActivity activity);

    void inject(LoginFragment fragment);

}
