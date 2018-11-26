package com.socket.dekaneh.fragment.offers;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Offer;

public class OffersFragmentVP {

    public interface View extends BaseView {
        void addOffers(List<Offer> offers);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchOffers();
    }
}
