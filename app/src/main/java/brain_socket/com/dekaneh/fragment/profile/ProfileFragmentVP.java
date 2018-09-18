package brain_socket.com.dekaneh.fragment.profile;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Order;

public class ProfileFragmentVP {

    public interface View extends BaseView {
        void addOrders(List<Order> orders);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchOrders();
    }
}
