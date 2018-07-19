package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @Expose
    @SerializedName("userId")
    private String userId;
    @Expose
    @SerializedName("user")
    private User user;

    public String getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }
}
