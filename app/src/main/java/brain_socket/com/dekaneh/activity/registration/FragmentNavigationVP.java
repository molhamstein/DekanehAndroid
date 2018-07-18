package brain_socket.com.dekaneh.activity.registration;

import brain_socket.com.dekaneh.fragment.BaseFragment;

public interface FragmentNavigationVP {

    interface View {

        void attachPresenter(Presenter presenter);

    }

    interface Presenter {

        void replaceFragment(BaseFragment fragment); // TODO: maybe we have to change it to addFragment and push to stack

    }
}
