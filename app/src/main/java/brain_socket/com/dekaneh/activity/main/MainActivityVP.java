package brain_socket.com.dekaneh.activity.main;

import android.support.annotation.StringRes;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Product;

public interface MainActivityVP {

    interface View extends BaseView {

        void showMainFragment();
        void showOffersFragment();
        void showCategoriesFragment();
        void showProfileFragment();
        void showToolbarTitle(boolean show);
        void showToolbarLogo(boolean show);
        void setToolbarTitle(String title);
        void setToolbarTitle(@StringRes int title);
        void recreate();
        void updateCartItemCount(String count);
        void updateSearchView(List<Product> products);
    }

    interface Presenter<T extends View> extends BasePresenter<T> {

        void onBottomNavMainItemClick();
        void onBottomOffersItemClick();
        void onBottomCategoriesItemClick();
        void onBottomProfileItemClick();
        void onResume();
        void restart();
        void search(String query);
    }

}
