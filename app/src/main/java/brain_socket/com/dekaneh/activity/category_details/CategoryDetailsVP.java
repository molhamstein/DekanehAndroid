package brain_socket.com.dekaneh.activity.category_details;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.SubCategory;

public class CategoryDetailsVP {

    public interface View extends BaseView {
        void addAllManufacturers(List<Manufacturer> manufacturers);
        void addAllSubCategories(List<SubCategory> subCategories);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchSubCategories();
        void fetchManufacturers();
    }
}
