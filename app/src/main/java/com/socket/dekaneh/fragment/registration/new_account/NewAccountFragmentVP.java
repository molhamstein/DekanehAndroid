package com.socket.dekaneh.fragment.registration.new_account;

import com.github.florent37.viewanimator.AnimationListener;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.Area;

public class NewAccountFragmentVP {

    public interface View extends BaseView {
        void onSuccessfulSignUp();
        void outAnimation(AnimationListener.Stop onStop);
        boolean areFieldsEmpty();
        void setAllAreas(List<Area> areas);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void signUp(String phoneNumber, String storeName, String ownerName, String location, String password, int areaPos);
        void fetchAreas();
        void openUrl();
    }
}
