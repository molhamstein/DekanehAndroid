package brain_socket.com.dekaneh.activity.registration;

import brain_socket.com.dekaneh.base.BaseFragment;

public class RegistrationActivityPresenter implements RegistrationActivityVP.Presenter, FragmentNavigationVP.Presenter{

    private RegistrationActivityVP.View view;

    RegistrationActivityPresenter(RegistrationActivityVP.View view) {
        this.view = view;
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        view.setFragment(fragment);
    }
}
