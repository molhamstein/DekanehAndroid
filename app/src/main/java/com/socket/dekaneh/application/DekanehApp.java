package com.socket.dekaneh.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatDelegate;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.socket.dekaneh.dagger.ApplicationComponent;
import com.socket.dekaneh.dagger.ApplicationModule;
import com.socket.dekaneh.dagger.DaggerApplicationComponent;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class DekanehApp extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

//        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).build();
//
//        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
//                .addNetworkInterceptor(new HttpLoggingInterceptor())
//                .build();

        AndroidNetworking.initialize(getApplicationContext());
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        AndroidNetworking.setParserFactory(new GsonParserFactory(gson));
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());


    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
