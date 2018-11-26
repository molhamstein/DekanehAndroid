package com.socket.dekaneh.activity.registration;

import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public interface RegistrationActivityVP {

    interface View extends BaseView {

        void setFragment(BaseFragment fragment);
        void startMainActivity();

        void finish();
    }

    interface Presenter<T extends View> extends BasePresenter<T> {
    }

}