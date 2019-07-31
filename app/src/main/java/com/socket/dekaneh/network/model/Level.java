
package com.socket.dekaneh.network.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Level implements Serializable {

    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = -2965075735656734511L;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}