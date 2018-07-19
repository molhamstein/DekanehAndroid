package brain_socket.com.dekaneh;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import brain_socket.com.dekaneh.activity.BaseActivity;


public class DekanehApp extends Application {
//    public static Context appContext;
//    private static Gson sharedGsonParser;
//    public static String systemLanguage;
    private static BaseActivity currentActivity;
//    private static boolean API_LOG = false;
//

    @Override
    public void onCreate() {
        super.onCreate();
//        appContext = this;
//        sharedGsonParser = new Gson();
        AndroidNetworking.initialize(getApplicationContext());
//        DataStore.disposeCurrentInstance(); /// to make sure no members are still stuck from the previous app instance
//        DataStore.getInstance();
//        systemLanguage = Locale.getDefault().getLanguage();
//
//        //load language from cache
//        String locale = DataStore.getInstance().getLocale();
//        if (!locale.isEmpty()) {
//            LocaleUtils.setLocale(new Locale(locale));
//            LocaleUtils.updateConfig(this, getResources().getConfiguration());
//        }
    }

//    public static String getSystemLanguage() {
//        return systemLanguage;
//    }
//
//    public static boolean isArabic() {
//        String locale = DataStore.getInstance().getLocale();
//        if(locale != "" && locale!= null)
//            return locale.equalsIgnoreCase("ar");
//        return App.getSystemLanguage().equalsIgnoreCase("ar");
//    }
//
//    public static String getLangCode() {
//        if (isArabic())
//            return "ar";
//        return "en";
//    }
//
//    public static Gson getSharedGsonParser() {
//        return sharedGsonParser;
//    }

    public static void setCurrentActivity(BaseActivity activity) {
        DekanehApp.currentActivity = activity;
    }

    public static BaseActivity getCurrentActivity() {
        return currentActivity;
    }

}
