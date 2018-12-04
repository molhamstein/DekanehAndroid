package com.socket.dekaneh.application;

import android.app.Application;
import android.content.res.Configuration;

import com.androidnetworking.AndroidNetworking;
import com.socket.dekaneh.dagger.ApplicationComponent;
import com.socket.dekaneh.dagger.ApplicationModule;
import com.socket.dekaneh.dagger.DaggerApplicationComponent;

import java.util.Locale;


public class DekanehApp extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());

        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());


    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
