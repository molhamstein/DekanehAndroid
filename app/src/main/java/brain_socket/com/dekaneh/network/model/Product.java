package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable{


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


    private String price;
    private boolean hasOffer;

    public Product() {
    }

    public Product(String price, boolean hasOffer) {
        this.price = price;
        this.hasOffer = hasOffer;
    }

    public Product(String price) {
        this.price = price;
        this.hasOffer = false;
    }

    public boolean isHasOffer() {
        return hasOffer;
    }

    public void setHasOffer(boolean hasOffer) {
        this.hasOffer = hasOffer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

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
