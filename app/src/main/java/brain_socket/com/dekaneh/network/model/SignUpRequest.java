package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignUpRequest implements Serializable {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("locationPoint")
    @Expose
    private LocationPoint locationPoint;
    @SerializedName("password")
    @Expose
    private String password;

    public SignUpRequest(String phoneNumber, String shopName, String ownerName, String password) {
        this.phoneNumber = phoneNumber;
        this.shopName = shopName;
        this.ownerName = ownerName;
        this.password = password;
    }
}
