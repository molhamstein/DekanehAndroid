package brain_socket.com.dekaneh.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import brain_socket.com.dekaneh.DekanehApp;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DekanehApp app);

    @ApplicationContext
    Context context();

    Application application();

}
