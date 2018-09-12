package brain_socket.com.dekaneh.activity.product_details;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Product;

public class ProductDetailsVP {

    public interface View extends BaseView{
        void updateView(Product product);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

    }

}
