package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AwardPrize implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("productId")
    @Expose
    private String productId;
    private final static long serialVersionUID = -1365117454367679480L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
