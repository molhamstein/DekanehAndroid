package com.socket.dekaneh.activity.splash;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public class SplashActivityVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void clearCache();
    }

}
