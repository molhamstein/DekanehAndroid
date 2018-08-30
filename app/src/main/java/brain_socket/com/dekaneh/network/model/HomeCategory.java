package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomeCategory implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("titleEn")
    @Expose
    private String titleEn;
    @SerializedName("titleAr")
    @Expose
    private String titleAr;
    @SerializedName("products")
    @Expose
    private List <Product> products;


    public String getId() {
        return id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleAr() {
        return titleAr;
    }

    public List<Product> getProducts() {
        return products;
    }
}
