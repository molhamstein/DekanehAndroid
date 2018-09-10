package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Offer implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("retailPrice")
    @Expose
    private String retailPrice;


    public String getId() {
        return id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public String getImage() {
        return image;
    }

    public String getRetailPrice() {
        return retailPrice;
    }
}
