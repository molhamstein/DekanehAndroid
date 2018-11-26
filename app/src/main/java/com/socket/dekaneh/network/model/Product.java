package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {


    public static final String TAG = Product.class.getSimpleName();
    @SerializedName("_id")
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
    private Media media;


    public Product(Product product) {
        this.id = product.getId();
        this.nameAr = product.getNameAr();
        this.horecaPrice = product.getHorecaPrice();
        this.wholeSalePrice = product.getWholeSalePrice();
        if (this.media != null)
            this.media.url = product.getMedia().url;
    }

    public Product(Offer offer) {
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.horecaPrice = offer.getHorecaPrice();
        this.horecaPriceDiscount = offer.getHorecaPriceDiscount();
        this.wholeSalePrice = offer.getWholeSalePrice();
        this.wholeSalePriceDiscount = offer.getWholeSalePriceDiscount();
        if (this.media != null)
            this.media.url = offer.getMedia().url;
    }


    public Product(Offer offer, Media media) {
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.horecaPrice = offer.getHorecaPrice();
        this.horecaPriceDiscount = offer.getHorecaPriceDiscount();
        this.wholeSalePrice = offer.getWholeSalePrice();
        this.wholeSalePriceDiscount = offer.getWholeSalePriceDiscount();
        this.manufacturer = offer.getManufacturer();
        this.media = media;
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

    public int getHorecaPrice() {
        return horecaPrice;
    }

    public int getWholeSalePrice() {
        return wholeSalePrice;
    }

    public int getHorecaPriceDiscount() {
        return horecaPriceDiscount;
    }

    public int getWholeSalePriceDiscount() {
        return wholeSalePriceDiscount;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Media getMedia() {
        if (media != null) {
            return media;
        }
        return new Media();
    }

    public String getPack() {
        return pack;
    }

    private int getWholeDiscountPercentage() {
        return (wholeSalePrice - wholeSalePriceDiscount) * 100 / wholeSalePrice;
    }

    public boolean isOffer() {
        return isOffer;
    }

    private int getHorecaDiscountPercentage() {
        return (horecaPrice - horecaPriceDiscount) * 100 / horecaPrice;
    }

    public String getWholePercentageString() {
        return String.valueOf(getWholeDiscountPercentage()) + "%";
    }

    public String getHorecaPercentageString() {
        return String.valueOf(getHorecaDiscountPercentage()) + "%";
    }

    public String getMarketOfficialPrice() {
        return marketOfficialPrice;
    }

    public class Media implements Serializable {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;



        public Media() {
            url = "";
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        @Override
        public String toString() {
            return "Media{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", horecaPrice=" + horecaPrice +
                ", horecaPriceDiscount=" + horecaPriceDiscount +
                ", wholeSalePrice=" + wholeSalePrice +
                ", wholeSalePriceDiscount=" + wholeSalePriceDiscount +
                ", manufacturer=" + manufacturer +
                ", description='" + description + '\'' +
                ", media=" + media +
                '}';
    }

    public int getPrice(boolean isHoreca) {
        if (isHoreca) return getHorecaPrice();
        else return getWholeSalePrice();
    }

}
