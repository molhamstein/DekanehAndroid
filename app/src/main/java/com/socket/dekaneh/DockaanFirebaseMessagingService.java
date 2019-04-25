package com.socket.dekaneh;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.socket.dekaneh.activity.rating.RatingActivity;
import com.socket.dekaneh.application.AppSchedulerProvider;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.Session;

import io.reactivex.functions.Consumer;

public class DockaanFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String action = remoteMessage.getData().get("openActivity");
        if (action != null)
            if (!action.isEmpty() && action.toLowerCase().equals("rating".toLowerCase())) {
                String orderId = remoteMessage.getData().get("orderId");
                if (orderId != null) {
                    RatingActivity.start(this, orderId);
                }
            }
    }


    @Override
    public void onNewToken(String s) {
        Session session = new Session(getApplicationContext());
        session.setFirebaseToken(s);
        //Log.d("ASDADQWEASDQWEASD", "onNewToken: " + s);
        super.onNewToken(s);
    }


}
