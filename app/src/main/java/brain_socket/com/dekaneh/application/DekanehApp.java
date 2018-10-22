package brain_socket.com.dekaneh.application;

import android.app.Application;
import android.content.res.Configuration;

import com.androidnetworking.AndroidNetworking;
import com.onesignal.OneSignal;

import java.util.Locale;

import brain_socket.com.dekaneh.DockaanNotificationOpenedHandler;
import brain_socket.com.dekaneh.DockaanNotificationReceivedHandler;
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

        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
//        config.setLayoutDirection(locale);
        getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new DockaanNotificationReceivedHandler())
                .setNotificationOpenedHandler(new DockaanNotificationOpenedHandler(getApplicationContext()))
                .init();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
