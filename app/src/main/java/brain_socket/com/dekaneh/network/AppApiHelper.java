package brain_socket.com.dekaneh.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Singleton;

import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private AppApiHelper(){ }

    public Single<LoginResponse> login(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN)
                .addBodyParameter(request)
                .addQueryParameter("include", "user")
                .build()
                .getObjectSingle(LoginResponse.class);
    }

}
