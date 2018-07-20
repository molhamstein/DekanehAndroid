package brain_socket.com.dekaneh.fragment;


import com.androidnetworking.error.ANError;

import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginFragmentPresenter implements LoginFragmentVP.Presenter{

    LoginFragmentVP.View view;

    public LoginFragmentPresenter(LoginFragmentVP.View view) {
        this.view = view;
    }

    @Override
    public void login(String phoneNumber, String password) {

    }
}
