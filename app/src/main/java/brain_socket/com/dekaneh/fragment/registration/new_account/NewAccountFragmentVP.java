package brain_socket.com.dekaneh.fragment.registration.new_account;

import com.github.florent37.viewanimator.AnimationListener;

import java.util.List;

import brain_socket.com.dekaneh.base.BasePresenter;
import brain_socket.com.dekaneh.base.BaseView;
import brain_socket.com.dekaneh.network.model.Area;

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
    }
}
