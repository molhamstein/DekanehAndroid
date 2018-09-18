package brain_socket.com.dekaneh.activity.cart;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.CartItem;

public class CartActivityVP {

    public interface View extends BaseView {
        void addAllItems(List<CartItem> items);
        void setOrderViewClear(boolean clear);
        void setOkResult();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchItems();
        void sendOrder();
        int getPrice();
    }
}
