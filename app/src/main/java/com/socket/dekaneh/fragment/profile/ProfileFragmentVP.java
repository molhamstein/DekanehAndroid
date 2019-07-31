package com.socket.dekaneh.fragment.profile;

import android.support.v4.app.FragmentManager;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Award;
import com.socket.dekaneh.network.model.Order;

import javax.inject.Singleton;

public class ProfileFragmentVP {


    public interface View extends BaseView {
        void addOrders(List<Order> orders);
        void updateView(String storeName, String ownerName, String phoneNumber,Integer balance,String level);
        void updateMap(String url);
//        void openDialog(String tag) ;
        void addAwards(List<Award> awards);
    }

    public interface Presenter<T extends View> extends BasePresenter<T> {
        void fetchOrders();
        void patchUser(String storeName, String ownerName, String phoneNumber);
        void fetchPastOrders();
        void fetchAwards();
        boolean hideHistory();
    }
}
