package brain_socket.com.dekaneh.activity.order_details;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.CartItem;

public class OrderDetailsVP {

    public interface View extends BaseView{
        void updateAllItems(List<CartItem> items);
        void updateView(String id, String creationDate, String status, String total);
        void edit(boolean edit);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchItems();
        void updateOrder();
        void updateProducts(List<CartItem> items);
    }
}