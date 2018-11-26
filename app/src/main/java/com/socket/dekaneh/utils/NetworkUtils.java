package com.socket.dekaneh.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.androidnetworking.error.ANError;

import com.socket.dekaneh.network.model.Error;

public class NetworkUtils {

    private NetworkUtils() { }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String getError(Throwable throwable) {

        if (throwable instanceof ANError) {
            ANError anError = (ANError) throwable;
            Error error =  anError.getErrorAsObject(Error.class);
            return error.getMessage();
        }
        return "Not Instance Of Error.class";
    }

    public static String getStaticMapUrl(String lat, String lng) {
        return "http://maps.google.com/maps/api/staticmap?center=" + lat + "," + lng + "&zoom=15&size=400x300&sensor=false&key=AIzaSyDyhbojoipNUc6r1H5lsm0KJpTqKrJ94DM";
    }

}
