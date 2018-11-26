package com.socket.dekaneh.activity.settings;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public class SettingsActivityVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
//        void logout();
        void offlineLogout();
    }
}
