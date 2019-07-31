package com.socket.dekaneh.base;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socket.dekaneh.BuildConfig;
import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.registration.FragmentNavigationVP;
import com.socket.dekaneh.dagger.ActivityComponent;
import butterknife.ButterKnife;
import com.socket.dekaneh.network.AppApiHelper;

public abstract class BaseFragment extends Fragment implements FragmentNavigationVP.View, BaseView {

    protected FragmentNavigationVP.Presenter navigationPresenter;
    BaseActivity activity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(rootViewLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        init(rootView);
        return rootView;
    }

    public abstract void init(View rootView);

    /**
     * e.g
     *
     * @return R.id.layout_name
     **/
    public abstract int rootViewLayoutId();

    public abstract String TAG();

    @Override
    public void attachPresenter(FragmentNavigationVP.Presenter presenter) {
        this.navigationPresenter = presenter;
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null) return activity.getActivityComponent();
        else return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Nullable
    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void showLoading() {
        if (activity != null) activity.showLoading();
    }

    @Override
    public void hideLoading() {
        if (activity != null) activity.hideLoading();
    }

    @Override
    public void onError(int resId) {
        if (activity != null) activity.onError(resId);
    }

    @Override
    public void onError(String message) {
        if (activity != null) activity.onError(message);
    }

    @Override
    public void handleVersionResponse(Integer errorCode) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        if (errorCode == AppApiHelper.SYSTEM_NOT_RUNNING) {

            builder.setTitle(getString(R.string.sys_not_running_title))
                    .setMessage(getString(R.string.sys_not_running_title))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .show();
        } else if (errorCode == AppApiHelper.INVALID_CLIENT) {
            builder.setTitle(getString(R.string.invalid_client_title))
                    .setMessage(getString(R.string.invalid_client_msg))
                    .setNeutralButton(getString(R.string.store_button), new DialogInterface.OnClickListener() {
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
        if (activity != null) activity.showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
        if (activity != null) activity.showMessage(resId);
    }

    @Override
    public boolean isNetworkConnected() {
        return activity != null && activity.isNetworkConnected();
    }

    @Override
    public void updateMainActivityCartItemsCount(String count) {
        activity.updateMainActivityCartItemsCount(count);
    }

    @Override
    public void hideKeyboard() {
        activity.hideKeyboard();
    }

    @Override
    public Intent getIntent() {
        return activity.getIntent();
    }

}