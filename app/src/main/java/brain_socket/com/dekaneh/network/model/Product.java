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
    @SerializedName("pack")
    @Expose
    private String pack;

    @SerializedName("media")
    @Expose
    private Media media;


    public Product(Product product) {
        this.id = product.getId();
        this.nameAr = product.getNameAr();
        this.retailPrice = product.getRetailPrice();
        if (this.media != null)
            this.media.url = product.getMedia().url;
    }

    public Product(Offer offer) {
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.retailPrice = offer.getRetailPrice();
        if (this.media != null)
            this.media.url = offer.getMedia().url;
    }


    public Product(Offer offer, String image) {
        this.id = offer.getId();
        this.nameAr = offer.getNameAr();
        this.retailPrice = offer.getRetailPrice();
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
                ", retailPrice=" + retailPrice +
                ", marketPrice=" + marketPrice +
                ", manufacturer=" + manufacturer +
                ", description='" + description + '\'' +
                ", media=" + media +
                '}';
    }
}
