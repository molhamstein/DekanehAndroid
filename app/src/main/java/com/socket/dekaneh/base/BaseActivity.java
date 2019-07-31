package com.socket.dekaneh.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;

import com.socket.dekaneh.BuildConfig;
import com.socket.dekaneh.dagger.ActivityComponent;
import com.socket.dekaneh.application.DekanehApp;
import com.socket.dekaneh.R;
import com.socket.dekaneh.dagger.ActivityModule;
import com.socket.dekaneh.dagger.DaggerActivityComponent;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.utils.LocaleUtils;
import com.socket.dekaneh.utils.NetworkUtils;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements LocaleUtils.LanguageListener, BaseView {

    private View vLoading;
    ProgressDialog mProgressDialog;
    private ActivityComponent activityComponent;

    public BaseActivity() {
        LocaleUtils.updateConfig(this);
    }

    public void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void onError(int resId) {
        displayCustomToast(resId);
    }

    @Override
    public void onError(String message) {
        displayCustomToast(message);
    }

    @Override
    public void handleVersionResponse(Integer errorCode) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        if (errorCode == AppApiHelper.WARNING_CLIENT) {
            builder.setTitle(getString(R.string.warning_client_title))
                    .setMessage(getString(R.string.warning_client_msg))
                    .setNeutralButton(getString(R.string.store_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.STORE_URL));
                            startActivity(intent);
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (errorCode == AppApiHelper.SYSTEM_NOT_RUNNING) {

            builder.setTitle(getString(R.string.sys_not_running_title))
                    .setMessage(getString(R.string.sys_not_running_title))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .show();
        } else if (errorCode == AppApiHelper.INVALID_CLIENT) {
            builder.setTitle(getString(R.string.invalid_client_title))
                    .setMessage(getString(R.string.invalid_client_msg))
                    .setNeutralButton(getString(R.string.store_button), new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.STORE_URL));
                            startActivity(intent);
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .show();
        }
    }

    @Override
    public void showMessage(String message) {
        displayCustomToast(message);
    }

    @Override
    public void showMessage(int resId) {
        displayCustomToast(resId);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        closeKeyBoard();
    }

    @Override
    public void updateMainActivityCartItemsCount(String count) {
        TextView cartItemsCount = findViewById(R.id.cartItemsCount);
        if (cartItemsCount != null) {
            cartItemsCount.setText(count);
            if (cartItemsCount.getText().equals("0")) {
                cartItemsCount.setVisibility(View.GONE);
            } else {
                cartItemsCount.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onLanguageChange() {
        this.recreate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clearNotifications();
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((DekanehApp) getApplication()).getApplicationComponent())
                .build();
        mProgressDialog = new ProgressDialog(this);
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    protected void onPause() {
        super.onPause();
//        overridePendingTransition(R.anim.anim_nothing, R.anim.anim_slide_out_to_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearNotifications();
    }

//    @Override
//    public void handleVersionResponse() {
//
//    }

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

        } catch (Exception ignored) {
        }
    }

    /////////////////////////
    //--- Loading
    /////////////////////////

    protected void showLoading(boolean show) {
        try {
            if (vLoading == null)
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

    private void displayCustomToast(String txt) {
        try {
            Toast toast = Toast.makeText(this, txt, Toast.LENGTH_LONG);
            toast.show();

        } catch (Exception ignored) {
        }
    }

    private void displayCustomToast(@StringRes int strRes) {
        if (strRes != 0) {
            displayCustomToast(getString(strRes));
        }
    }

    private static void displaySnackBar(@StringRes int txtRes) {
        final Snackbar bar = Snackbar.make(null, txtRes, Snackbar.LENGTH_SHORT);
        bar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.dismiss();
            }
        });
        bar.show();
    }


    private static void displaySnackBar(String txt) {
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

    private void closeKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void hideKeyboardWhenTouchOut(View parentView) {
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

    private void showProgressDialog() {
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        mProgressDialog.dismiss();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context context) {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    @Override
    protected void onDestroy() {
        mProgressDialog.dismiss();
        super.onDestroy();
    }
}

