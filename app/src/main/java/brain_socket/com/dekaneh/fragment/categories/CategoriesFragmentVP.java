package brain_socket.com.dekaneh.fragment.categories;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Category;

public class CategoriesFragmentVP {

    public interface View extends BaseView {
        void addCategories(List<Category> categories);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchCategories();
    }
}
