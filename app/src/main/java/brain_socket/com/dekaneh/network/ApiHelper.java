package brain_socket.com.dekaneh.network;

import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import io.reactivex.Single;

public interface ApiHelper {

    Single<LoginResponse> login(LoginRequest request);

}
