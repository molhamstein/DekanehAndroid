package brain_socket.com.dekaneh.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.androidnetworking.error.ANError;

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
            ANError error = (ANError) throwable;
            return error.getErrorBody();
        }
        return "Not Instance Of ANError.class";
    }

}
