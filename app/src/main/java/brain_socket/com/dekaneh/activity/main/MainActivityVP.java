package brain_socket.com.dekaneh.activity.main;

import android.support.annotation.StringRes;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public interface MainActivityVP {

    interface View extends BaseView {

        void showMainFragment();
        void showOffersFragment();
        void showCategoriesFragment();
        void showToolbarTitle(boolean show);
        void showToolbarLogo(boolean show);
        void setToolbarTitle(String title);
        void setToolbarTitle(@StringRes int title);

    }

    interface Presenter<T extends View> extends BasePresenter<T> {

        void onBottomNavMainItemClick();
        void onBottomOffersItemClick();
        void onBottomCategoriesItemClick();

    }

}