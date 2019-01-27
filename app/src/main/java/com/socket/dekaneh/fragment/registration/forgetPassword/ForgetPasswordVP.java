package com.socket.dekaneh.fragment.registration.forgetPassword;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public class ForgetPasswordVP {

    public interface View extends BaseView {
        void backToLogin();
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void forgetPasswordRequest(String phoneNumber);
    }
}
