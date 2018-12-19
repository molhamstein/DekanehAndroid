package com.socket.dekaneh.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.socket.dekaneh.R;

public class ViewUtils {

    public static View getTabTextView(Context context, String title) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tab, null);
        textView.setTextSize(14f);
        textView.setText(title);
        return textView;
    }

    public static int getPXSize(int dp, Context context) {
        int px = dp;
        try {
            float density = context.getResources().getDisplayMetrics().density;
            px = Math.round((float) dp * density);
        } catch (Exception ignored) {
        }
        return px;
    }


    public static void makeCall(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public static void openUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }




}
