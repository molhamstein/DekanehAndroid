package brain_socket.com.dekaneh.fragment.registration.new_account;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public class NewAccountFragmentVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void signUp(String phoneNumber, String storeName, String ownerName, String location, String password);
    }
}
