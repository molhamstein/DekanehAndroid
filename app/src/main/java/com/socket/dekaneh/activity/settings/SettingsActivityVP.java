package com.socket.dekaneh.activity.settings;

import android.content.Context;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public class SettingsActivityVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void offlineLogout();
        void callSupport(Context context);
        void openUrl(Context context);
    }
}
