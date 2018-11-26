package com.socket.dekaneh.activity.cart;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.CartItem;

public class CartActivityVP {

    public interface View extends BaseView {
        void addAllItems(List<CartItem> items);
        void setOrderViewClear(boolean clear);
        void setOkResult();
        void disableOrderBtn();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchItems();
        void sendOrder();
        int getPrice();
    }
}
