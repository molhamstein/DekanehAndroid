package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {


    @Expose
    @SerializedName("ownerName")
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }
}
