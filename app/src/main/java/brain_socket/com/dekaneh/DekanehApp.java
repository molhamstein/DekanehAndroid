package brain_socket.com.dekaneh;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import brain_socket.com.dekaneh.base.BaseActivity;


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
