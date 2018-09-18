package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ttl")
    @Expose
    private String ttl;
    @SerializedName("user")
    @Expose
    private User user;

    public String getId() {
        return id;
    }

    public String getTtl() {
        return ttl;
    }

    public User getUser() {
        return user;
    }
}
