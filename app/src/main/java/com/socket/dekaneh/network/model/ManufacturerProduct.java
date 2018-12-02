package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ManufacturerProduct implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("horecaPrice")
    @Expose
    private int horecaPrice;
    @SerializedName("horecaPriceDiscount")
    @Expose
    private int horecaPriceDiscount;
    @SerializedName("wholeSalePrice")
    @Expose
    private int wholeSalePrice;
    @SerializedName("wholeSalePriceDiscount")
    @Expose
    private int wholeSalePriceDiscount;
    @SerializedName("isOffer")
    @Expose
    private boolean isOffer;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pack")
    @Expose
    private String pack;
    @SerializedName("marketOfficialPrice")
    @Expose
    private String marketOfficialPrice;

    @SerializedName("media")
    @Expose
    private Product.Media media;

    public String getId() {
        return id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public int getHorecaPrice() {
        return horecaPrice;
    }

    public int getHorecaPriceDiscount() {
        return horecaPriceDiscount;
    }

    public int getWholeSalePrice() {
        return wholeSalePrice;
    }

    public int getWholeSalePriceDiscount() {
        return wholeSalePriceDiscount;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public String getPack() {
        return pack;
    }

    public String getMarketOfficialPrice() {
        return marketOfficialPrice;
    }

    public Product.Media getMedia() {
        return media;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int getDiscountPercentage(String clientType) {
        if (clientType.equals(User.Type.retailCostumer.toString())) {
            if (getHorecaPrice() != 0 && getHorecaPriceDiscount() != 0)
                return (int) (getHorecaPrice() - getHorecaPriceDiscount()) * 100 / getHorecaPrice();
        } else if (getWholeSalePrice() != 0 && getWholeSalePriceDiscount() != 0)
            return (int) (getWholeSalePrice() - getWholeSalePriceDiscount()) * 100 / getWholeSalePrice();
        return 0;
    }

    public String getPercentageString(String clientType) {
        return String.valueOf(getDiscountPercentage(clientType)) + "%";
    }


    public Product getAsProduct() {
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setHorecaPrice(horecaPrice);
        product.setHorecaPriceDiscount(horecaPriceDiscount);
        product.setWholeSalePrice(wholeSalePrice);
        product.setWholeSalePriceDiscount(wholeSalePriceDiscount);
        product.setManufacturer(manufacturer);
        product.setMedia(media);
        product.setMarketOfficialPrice(marketOfficialPrice);
        product.setNameAr(nameAr);
        product.setPack(pack);
        return product;
    }


    @Override
    public String toString() {
        return "ManufacturerProduct{" +
                "id='" + id + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", horecaPrice=" + horecaPrice +
                ", horecaPriceDiscount=" + horecaPriceDiscount +
                ", wholeSalePrice=" + wholeSalePrice +
                ", wholeSalePriceDiscount=" + wholeSalePriceDiscount +
                ", isOffer=" + isOffer +
                ", manufacturer=" + manufacturer +
                ", description='" + description + '\'' +
                ", pack='" + pack + '\'' +
                ", marketOfficialPrice='" + marketOfficialPrice + '\'' +
                ", media=" + media +
                '}';
    }
}
