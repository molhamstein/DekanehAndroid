package brain_socket.com.dekaneh.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brain_socket.com.dekaneh.activity.main.MainActivity;
import brain_socket.com.dekaneh.activity.registration.FragmentNavigationVP;
import brain_socket.com.dekaneh.dagger.ActivityComponent;
import butterknife.ButterKnife;

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
    public void hideKeyboard() {
        activity.hideKeyboard();
    }

    @Override
    public Intent getIntent() {
        return activity.getIntent();
    }

}