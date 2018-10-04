package brain_socket.com.dekaneh.activity.product_details;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Product;

public class ProductDetailsVP {

    public interface View extends BaseView{
        void updateView(Product product);
        void addAllSimilarProducts(List<Product> products);
        void updateOrderCountText(int count);
        void hideSimilarProductsSection();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchProduct();
        void fetchSimilarProducts(String id);
        void onMinusBtnClicked();
        void onPlusBtnClicked();
    }

}
