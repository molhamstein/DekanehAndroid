package com.socket.dekaneh.activity.featured_products;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Product;

import java.util.List;

public class FeaturedActivityVP {
    
    public interface View extends BaseView {
        void addAllProducts(List<Product> products);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchProducts();
    }

}
