package com.socket.dekaneh.fragment.categories;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Category;

public class CategoriesFragmentVP {

    public interface View extends BaseView {
        void addCategories(List<Category> categories);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchCategories();
    }
}
