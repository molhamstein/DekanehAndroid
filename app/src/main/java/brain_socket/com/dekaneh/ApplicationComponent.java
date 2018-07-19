package brain_socket.com.dekaneh;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DekanehApp app);

    @ApplicationContext
    Context context();

    Application application();

}
