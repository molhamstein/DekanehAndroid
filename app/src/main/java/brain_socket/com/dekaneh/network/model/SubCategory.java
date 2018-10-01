package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubCategory implements Serializable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("titleAr")
    @Expose
    private String titleAr;
    @SerializedName("titleEn")
    @Expose
    private String titleEn;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("parentCategoryId")
    @Expose
    private String parentCategoryId;

    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public String getTitleAr() {
        return titleAr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getIcon() {
        return icon;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }
}
