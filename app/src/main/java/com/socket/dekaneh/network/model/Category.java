package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable{

    public static final String TAG = Category.class.getSimpleName();
    @SerializedName("titleAr")
    @Expose
    private String titleAr;
    @SerializedName("titleEn")
    @Expose
    private String titleEn;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("icon")
    @Expose
    private String icon;

    public String getTitleAr() {
        return titleAr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }
}