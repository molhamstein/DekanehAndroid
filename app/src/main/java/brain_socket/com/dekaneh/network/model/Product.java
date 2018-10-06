package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable{


    public static final String TAG = Product.class.getSimpleName();
    @SerializedName("_id")
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
    @SerializedName("marketPrice")
    @Expose
    private int marketPrice;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("description")
    @Expose
    private String description;



    public Product(Product product){
        this.id = product.getId();
        this.nameAr = product.getNameAr();
        this.retailPrice = product.getRetailPrice();
    }


    public Product(Offer offer, String image){
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.retailPrice = offer.getRetailPrice();
        this.manufacturer = offer.getManufacturer();
        this.image = image;
        this.description = offer.getDescription();
    }

    public Product(String id) {
        this.id = id;
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

    public int getMarketPrice() {
        return marketPrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }
}
