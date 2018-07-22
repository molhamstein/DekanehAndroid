package brain_socket.com.dekaneh.fragment.login;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public class LoginFragmentVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends View> extends BasePresenter<T>  {

        void login(String phoneNumber, String password);

    }
}
