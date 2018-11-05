package brain_socket.com.dekaneh.network.model;

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
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("description")
    @Expose
    private String description;
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
        this.wholeSalePrice = offer.getWholeSalePrice();
        if (this.media != null)
            this.media.url = offer.getMedia().url;
    }


    public Product(Offer offer, String image) {
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.horecaPrice = offer.getHorecaPrice();
        this.wholeSalePrice = offer.getWholeSalePrice();
        this.manufacturer = offer.getManufacturer();
        if (this.media != null)
            this.media.url = image;
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

    public Offer.Media getMedia() {
        return media;
    }

    private int getWholeDiscountPercentage() {
        return (wholeSalePrice - wholeSalePriceDiscount) * 100 / wholeSalePrice;
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

    public class Media implements Serializable {

        @SerializedName("url")
        @Expose
        private String url;

        public Media() {
            url = "";
        }

        public String getUrl() {
            return url;
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
}
