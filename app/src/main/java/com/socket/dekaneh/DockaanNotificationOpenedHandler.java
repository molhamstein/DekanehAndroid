package com.socket.dekaneh;

import android.content.Context;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import com.socket.dekaneh.activity.main.MainActivity;
import com.socket.dekaneh.activity.rating.RatingActivity;
import com.socket.dekaneh.activity.registration.RegistrationActivity;
import com.socket.dekaneh.network.Session;

public class DockaanNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

    private Context context;

    public DockaanNotificationOpenedHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String rate;

        if (data != null) {
            rate = data.optString("openActivity", null);
            String orderId = data.optString("orderId", null);
            if (rate != null)
                if (rate.equals("Rating".toLowerCase())) {
                    Log.i("OneSignalExample", "customkey set with value: " + rate);
                    RatingActivity.start(context, orderId);
                } else onEmptyNotification();
        } else onEmptyNotification();


        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
        // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(intent);


        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }


    public void onEmptyNotification() {
        RegistrationActivity.start(context);
    }
}