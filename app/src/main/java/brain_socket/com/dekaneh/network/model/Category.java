package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable{

    @SerializedName("titleAr")
    @Expose
    private String titleAr;
    @SerializedName("titleEn")
    @Expose
    private String titleEn;
    @SerializedName("id")
    @Expose
    private String id;

    public String getTitleAr() {
        return titleAr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getId() {
        return id;
    }
}