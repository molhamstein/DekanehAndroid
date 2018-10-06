package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Offer extends Product implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("wholeSalePrice")
    @Expose
    private double wholeSalePrice;
    @SerializedName("wholeSaleMarketPrice")
    @Expose
    private double wholeSaleMarketPrice;
    @SerializedName("retailPriceDiscount")
    @Expose
    private double retailPriceDiscount;
    @SerializedName("wholeSalePriceDiscount")
    @Expose
    private double wholeSalePriceDiscount;
    @SerializedName("isFeatured")
    @Expose
    private boolean isFeatured;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("products")
    @Expose
    private List<Product> products;


    public Offer(Offer offer) {
        super(offer);
    }

    @Override
    public String getId() {
        return id;
    }

    public double getWholeSalePrice() {
        return wholeSalePrice;
    }

    public double getWholeSaleMarketPrice() {
        return wholeSaleMarketPrice;
    }

    public double getRetailPriceDiscount() {
        return retailPriceDiscount;
    }

    public double getWholeSalePriceDiscount() {
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

    public List<Product> getProducts() {
        return products;
    }

    private int getDiscountPercentage() {
        return (int) (getRetailPrice() - retailPriceDiscount) * 100 / getRetailPrice();
    }

    public String getPercentageString() {
        return String.valueOf(getDiscountPercentage()) + "%";
    }
}
