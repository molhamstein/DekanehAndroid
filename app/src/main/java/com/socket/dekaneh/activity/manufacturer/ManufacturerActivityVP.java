package com.socket.dekaneh.activity.manufacturer;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Offer;

public class ManufacturerActivityVP {

    public interface View extends BaseView {
        void addAllProducts(List<Offer> offers);
        void setTitle(String title);

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchProducts();
    }

}
