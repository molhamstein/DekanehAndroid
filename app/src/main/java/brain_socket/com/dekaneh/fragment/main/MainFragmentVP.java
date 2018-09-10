package brain_socket.com.dekaneh.fragment.main;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;

public class MainFragmentVP {

    public interface View extends BaseView{
        void addCategoriesWithProducts(List<HomeCategory> categories);
        void addFeaturedOffers(List<Offer> offers);
    }

    public interface Presenter<T extends View> extends BasePresenter<T>{
        void fetchCategories();
        void fetchFeaturedOffers();
    }
}
