package com.socket.dekaneh.network;

import com.socket.dekaneh.network.model.LoginRequest;
import com.socket.dekaneh.network.model.LoginResponse;
import io.reactivex.Single;

public interface ApiHelper {

    Single<LoginResponse> login(LoginRequest request);

}
