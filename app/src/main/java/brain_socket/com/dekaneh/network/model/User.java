package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("locationPoint")
    @Expose
    private LocationPoint locationPoint;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("gender")
    @Expose
    private String gender;

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getClientType() {
        return clientType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getShopName() {
        return shopName;
    }

    public LocationPoint getLocationPoint() {
        return locationPoint;
    }

    public String getNotes() {
        return notes;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }
}
