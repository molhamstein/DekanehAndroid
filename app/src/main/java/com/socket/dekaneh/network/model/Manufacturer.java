package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Manufacturer implements Serializable {

    public static final String TAG = Manufacturer.class.getSimpleName();
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("products")
    @Expose
    private List<Product> products;

    public Manufacturer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public List<Product> getProducts() {
        return products;
    }


    @Override
    public String toString() {
        return "Manufacturer{" +
                "id='" + id + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", products=" + products +
                '}';
    }
}



