package com.socket.dekaneh.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import com.socket.dekaneh.application.DekanehApp;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DekanehApp app);

    @ApplicationContext
    Context context();

    Application application();

}
