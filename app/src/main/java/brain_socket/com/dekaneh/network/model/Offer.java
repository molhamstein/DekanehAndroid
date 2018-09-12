package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Offer implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("retailPrice")
    @Expose
    private int retailPrice;
    @SerializedName("wholeSalePrice")
    @Expose
    private int wholeSalePrice;
    @SerializedName("wholeSaleMarketPrice")
    @Expose
    private int wholeSaleMarketPrice;
    @SerializedName("retailPriceDiscount")
    @Expose
    private int retailPriceDiscount;
    @SerializedName("wholeSalePriceDiscount")
    @Expose
    private int wholeSalePriceDiscount;
    @SerializedName("isFeatured")
    @Expose
    private boolean isFeatured;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("products")
    @Expose
    private List<Product> products;

    public String getId() {
        return id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public String getDescription() {
        return description;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public int getWholeSalePrice() {
        return wholeSalePrice;
    }

    public int getWholeSaleMarketPrice() {
        return wholeSaleMarketPrice;
    }

    public int getRetailPriceDiscount() {
        return retailPriceDiscount;
    }

    public int getWholeSalePriceDiscount() {
        return wholeSalePriceDiscount;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public String getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public List<Product> getProducts() {
        return products;
    }

    private int getDiscountPercentage() {
        return (retailPrice - retailPriceDiscount) * 100 / retailPrice;
    }

    public String getPercentageString() {
        return String.valueOf(getDiscountPercentage()) + "%";
    }
}
