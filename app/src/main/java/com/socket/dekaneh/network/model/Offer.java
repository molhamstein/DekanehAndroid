package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Offer extends Product implements Serializable {

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

    private int getDiscountPercentage(String clientType) {
        if (clientType.equals(User.Type.horeca.toString())) {
            if (getHorecaPrice() != 0 && getHorecaPriceDiscount() != 0)
                return (int) (getHorecaPrice() - getHorecaPriceDiscount()) * 100 / getHorecaPrice();
        } else if (getWholeSalePrice() != 0 && getWholeSalePriceDiscount() != 0)
            return (int) (getWholeSalePrice() - getWholeSalePriceDiscount()) * 100 / getWholeSalePrice();
        return 0;
    }

    public String getPercentageString(String clientType) {
        return String.valueOf(getDiscountPercentage(clientType)) + "%";
    }


    @Override
    public String toString() {
        return "Offer{" +
                "id='" + getId() + '\'' +
                "super=" + super.toString() +
                ", isFeatured=" + isFeatured +
                ", status='" + status + '\'' +
                ", category=" + category +
                ", products=" + products +
                '}';
    }
}
