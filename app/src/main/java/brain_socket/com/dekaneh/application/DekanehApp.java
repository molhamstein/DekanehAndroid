package brain_socket.com.dekaneh.application;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import brain_socket.com.dekaneh.dagger.ApplicationComponent;
import brain_socket.com.dekaneh.dagger.ApplicationModule;
import brain_socket.com.dekaneh.dagger.DaggerApplicationComponent;


public class DekanehApp extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
