package brain_socket.com.dekaneh.activity.product_details;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;

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
