package com.socket.dekaneh.fragment.main;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.SliderImage;

public class MainFragmentVP {

    public interface View extends BaseView{
        void addCategoriesWithProducts(List<HomeCategory> categories);
        void addFeaturedOffers(List<Offer> offers);
        void addSliderImages(List<SliderImage> images);
    }

    public interface Presenter<T extends View> extends BasePresenter<T>{
        void fetchCategories();
        void fetchFeaturedOffers();
        void fetchSliderImages();
    }
}
