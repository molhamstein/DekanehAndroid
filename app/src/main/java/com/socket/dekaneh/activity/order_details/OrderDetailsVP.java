package com.socket.dekaneh.activity.order_details;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.CartItem;

public class OrderDetailsVP {

    public interface View extends BaseView{
        void updateAllItems(List<CartItem> items);
        void updateView(String id, String creationDate, String status, String total);
        void edit(boolean edit);
        void finish();
        void addCoupon(String code, String value, String priceBeforeDiscount);

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchItems();
        void updateOrder();
        void updateProducts(List<CartItem> items);
        void cancelOrder();
        boolean checkOrderCancelOptionAvailable();
    }
}