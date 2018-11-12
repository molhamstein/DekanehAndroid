package brain_socket.com.dekaneh.activity.rating;

import brain_socket.com.dekaneh.Rating;
import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;

public class RatingActivityVP {

    public interface View extends BaseView {

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void submitRate();
        void setRate(Rating.Rate rate);
    }

}
