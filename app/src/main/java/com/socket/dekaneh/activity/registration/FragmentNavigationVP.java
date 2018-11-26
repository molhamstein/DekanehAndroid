package com.socket.dekaneh.activity.registration;

import com.socket.dekaneh.base.BaseFragment;

public interface FragmentNavigationVP {

    interface View {

        void attachPresenter(Presenter presenter);

    }

    interface Presenter {

        void replaceFragment(BaseFragment fragment); // TODO: maybe we have to change it to addFragment and push to stack

    }
}
