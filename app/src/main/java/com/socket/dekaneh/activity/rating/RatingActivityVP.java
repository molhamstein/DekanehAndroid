package com.socket.dekaneh.activity.rating;

import com.socket.dekaneh.Rating;
import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;

public class RatingActivityVP {

    public interface View extends BaseView {
        void finish();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void submitRate();

        void setRate(Rating.Rate rate);
    }

}
