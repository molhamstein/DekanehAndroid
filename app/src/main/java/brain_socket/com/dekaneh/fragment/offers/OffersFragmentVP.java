package brain_socket.com.dekaneh.fragment.offers;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Offer;

public class OffersFragmentVP {

    public interface View extends BaseView {
        void addOffers(List<Offer> offers);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchOffers();
    }
}
