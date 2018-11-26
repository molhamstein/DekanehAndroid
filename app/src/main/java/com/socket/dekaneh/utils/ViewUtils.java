package com.socket.dekaneh.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.socket.dekaneh.R;

public class ViewUtils {

    public static View getTabTextView(Context context, String title){
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tab, null);
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

}
