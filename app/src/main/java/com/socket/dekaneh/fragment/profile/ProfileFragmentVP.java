package com.socket.dekaneh.fragment.profile;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Order;

public class ProfileFragmentVP {

    public interface View extends BaseView {
        void addOrders(List<Order> orders);
        void updateView(String storeName, String ownerName, String phoneNumber);
        void updateMap(String url);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchOrders();
        void patchUser(String storeName, String ownerName, String phoneNumber);
        void fetchPastOrders();
        boolean hideHistory();
    }
}
