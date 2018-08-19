package brain_socket.com.dekaneh.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import brain_socket.com.dekaneh.R;

public class ViewUtils {

    public static View getTabTextView(Context context, String title){
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tab, null);
        textView.setText(title);
        return textView;
    }

}
