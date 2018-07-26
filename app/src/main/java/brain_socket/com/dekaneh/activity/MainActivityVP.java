package brain_socket.com.dekaneh.activity;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public interface MainActivityVP {

    interface View extends BaseView {

        void showMainFragment();

    }

    interface Presenter<T extends View> extends BasePresenter<T> {

        void onBottomNavMainItemClick();

    }

}
