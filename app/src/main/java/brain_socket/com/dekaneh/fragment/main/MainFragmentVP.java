package brain_socket.com.dekaneh.fragment.main;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.HomeCategory;

public class MainFragmentVP {

    public interface View extends BaseView{
        void addCategoriesWithProducts(List<HomeCategory> categories);
    }

    public interface Presenter<T extends View> extends BasePresenter<T>{
        void fetchCategories();
    }
}
