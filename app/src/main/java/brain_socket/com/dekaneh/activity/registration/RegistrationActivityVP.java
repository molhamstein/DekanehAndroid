package brain_socket.com.dekaneh.activity.registration;

import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public interface RegistrationActivityVP {

    interface View extends BaseView {

        void setFragment(BaseFragment fragment);
        void startMainActivity();

        void finish();
    }

    interface Presenter<T extends View> extends BasePresenter<T> {
    }

}