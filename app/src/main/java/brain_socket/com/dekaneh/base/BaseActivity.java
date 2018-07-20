package brain_socket.com.dekaneh.base;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import brain_socket.com.dekaneh.ActivityComponent;
import brain_socket.com.dekaneh.ActivityModule;
import brain_socket.com.dekaneh.DaggerActivityComponent;
import brain_socket.com.dekaneh.DekanehApp;
import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.utils.LocaleUtils;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements LocaleUtils.LanguageListener {

    private View vLoading;
    ProgressDialog mProgressDialog;
    private ActivityComponent activityComponent;

    public BaseActivity() {
        LocaleUtils.updateConfig(this);
    }

    private void hideStatusBar(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onLanguageChange() {
        this.recreate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        clearNotifications();
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((DekanehApp) getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_nothing, R.anim.anim_slide_out_to_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearNotifications();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    void clearNotifications() {
        try {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getSystemService(ns);
            nMgr.cancelAll();

        } catch (Exception ignored) {}
    }

    /////////////////////////
    //--- Loading
    /////////////////////////

    protected void showLoading(boolean show) {
        try {
            if(vLoading == null)
                ///vLoading = findViewById(R.id.vLoading);
            if (show) {
                vLoading.setVisibility(View.VISIBLE);
            } else {
                vLoading.setVisibility(View.GONE);
            }
        } catch (Exception ignored) {

        }
    }

    /////////////////////////
    //--- Alerts & toasts
    /////////////////////////

    public void displayCustomToast(String txt) {
        try {
            Toast toast = Toast.makeText(this, txt, Toast.LENGTH_LONG);
            toast.show();

        } catch (Exception ignored) {
        }
    }

    public void displayCustomToast(int strRes) {
        if (strRes != 0) {
            displayCustomToast(getString(strRes));
        }
    }

    public static void displaySnackBar(String txt) {
        final Snackbar bar = Snackbar.make(null, txt, Snackbar.LENGTH_SHORT);
        bar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.dismiss();
            }
        });
        bar.show();
    }

    /////////////////////////
    //--- Keyboard
    /////////////////////////

    void closeKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void hideKeyboardWhenTouchOut(View parentView) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(parentView instanceof EditText)) {
            parentView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    closeKeyBoard();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (parentView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) parentView).getChildCount(); i++) {
                View innerView = ((ViewGroup) parentView).getChildAt(i);
                hideKeyboardWhenTouchOut(innerView);
            }
        }
    }

    public void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void hideProgressDialog(){
        mProgressDialog.dismiss();
    }

}

