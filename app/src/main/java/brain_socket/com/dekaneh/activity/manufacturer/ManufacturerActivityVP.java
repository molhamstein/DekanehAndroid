package brain_socket.com.dekaneh.activity.manufacturer;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;

public class ManufacturerActivityVP {

    public interface View extends BaseView {
        void addAllProducts(List<Offer> offers);
        void setTitle(String title);

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchProducts();
    }

}
