package com.socket.dekaneh.activity.product_details;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;

public class ProductDetailsVP {

    public interface View extends BaseView{
        void updateView(Product product, String imageUrl, boolean isHoreca);
        void addAllSimilarProducts(List<Product> products);
        void addAllOffers(List<Offer> offers);
        void updateOrderCountText(int count);
        void hideSimilarProductsSection();
        void hideOffersSection();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchProduct();
        void fetchOffers();
        void fetchSimilarProducts();
        void onMinusBtnClicked();
        void onPlusBtnClicked();
    }

}
