package com.socket.dekaneh.activity.category_details;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.network.model.SubCategory;

public class CategoryDetailsVP {

    public interface View extends BaseView {
        void addAllManufacturers(List<Manufacturer> manufacturers);
        void addAllSubCategories(List<SubCategory> subCategories);
        void setTitle(String title);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchSubCategories();
        void fetchManufacturers();
        void fetchManufacturers(String subCategoryId);
    }
}
