package brain_socket.com.dekaneh.base;

import com.androidnetworking.error.ANError;

public interface BasePresenter<T extends BaseView> {

    void onAttach(T mvpView);

    void onDetach();

    void handleApiError(ANError error);


}
