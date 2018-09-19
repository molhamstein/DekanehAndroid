package brain_socket.com.dekaneh.activity.settings;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public class SettingsActivityVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void logout();
    }
}
