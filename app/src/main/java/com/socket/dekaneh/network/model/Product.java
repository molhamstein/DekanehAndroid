package com.socket.dekaneh.network.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {


    public static final String TAG = Product.class.getSimpleName();
//    public static final int INFINITE_QUANTITY = -1;
    public static final int INFINITE_QUANTITY = Integer.MAX_VALUE;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("_id")
    @Expose
    private String _id;
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
    @SerializedName("offersIds")
    @Expose
    private List<String> offersIds;
    @SerializedName("isFavorite")
    @Expose
    private boolean favorite;
    @SerializedName("offerMaxQuantity")
    @Expose
    private String offerMaxQuantity;



    @SerializedName("media")
    @Expose
    private Media media;

    public Product() {
    }

    public Product(Product product) {
        this.id = product.getId();
        this.nameAr = product.getNameAr();
        this.horecaPrice = product.getHorecaPrice();
        this.horecaPriceDiscount = product.getHorecaPriceDiscount();
        this.wholeSalePrice = product.getWholeSalePrice();
        this.wholeSalePriceDiscount = product.getWholeSalePriceDiscount();
        if (product.media != null)
            this.media = product.getMedia();
        this.offerMaxQuantity = product.getOfferMaxQuantityString();
        this.isOffer = product.isOffer();

    }

    public Product(Offer offer) {
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.horecaPrice = offer.getHorecaPrice();
        this.horecaPriceDiscount = offer.getHorecaPriceDiscount();
        this.wholeSalePrice = offer.getWholeSalePrice();
        this.wholeSalePriceDiscount = offer.getWholeSalePriceDiscount();
        this.offerMaxQuantity = offer.getOfferMaxQuantityString();
        if (this.media != null)
            this.media.url = offer.getMedia().url;
        this.isOffer = offer.isOffer();
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
        this.offerMaxQuantity = offer.getOfferMaxQuantityString();
        this.isOffer = offer.isOffer();
    }

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        if(id == null) return _id;
        else return id;
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
        if (isHoreca){
            if (hasDiscount(User.Type.horeca.toString()))
                return getHorecaPriceDiscount();
            return getHorecaPrice();
        }
        else {
            if (hasDiscount(User.Type.wholesale.toString())) {
                return getWholeSalePriceDiscount();
            }
            return getWholeSalePrice();
        }
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public void setHorecaPrice(int horecaPrice) {
        this.horecaPrice = horecaPrice;
    }

    public void setHorecaPriceDiscount(int horecaPriceDiscount) {
        this.horecaPriceDiscount = horecaPriceDiscount;
    }

    public void setWholeSalePrice(int wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public void setWholeSalePriceDiscount(int wholeSalePriceDiscount) {
        this.wholeSalePriceDiscount = wholeSalePriceDiscount;
    }

    public void setOffer(boolean offer) {
        isOffer = offer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public void setMarketOfficialPrice(String marketOfficialPrice) {
        this.marketOfficialPrice = marketOfficialPrice;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<String> getOffersIds() {
        return offersIds;
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

    public boolean hasDiscount(String clientType) {
        if (clientType.equals(User.Type.horeca.toString())) {
            return getHorecaPrice() != 0 && getHorecaPriceDiscount() != 0 && getHorecaPrice() != getHorecaPriceDiscount();
        } else {
            return getWholeSalePrice() != 0 && getWholeSalePriceDiscount() != 0 && getWholeSalePrice() != getWholeSalePriceDiscount();
        }
    }
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getOfferMaxQuantity() {
        Log.d(TAG, "getOfferMaxQuantity: =  " + offerMaxQuantity + " "  + isOffer);
        if (isOffer) {
            try {
                return Integer.valueOf(offerMaxQuantity);
            } catch(Exception ignored){}
        }
        return INFINITE_QUANTITY;
    }

    public String getOfferMaxQuantityString() {
        return offerMaxQuantity;
    }



}
