package com.socket.dekaneh.fragment.registration.login;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public class LoginFragmentVP {

    public interface View extends BaseView {

        void startMainActivity();

    }

    public interface Presenter<T extends View> extends BasePresenter<T>  {

        void login(String phoneNumber, String password);

    }
}
