package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable{


    public static final String TAG = Product.class.getSimpleName();
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
    private int retailPrice;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;

    public Product() { }

    public Product(Product product){
        this.id = product.getId();
        this.nameAr = product.getNameAr();
        this.retailPrice = product.getRetailPrice();
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

    public int getRetailPrice() {
        return retailPrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}
