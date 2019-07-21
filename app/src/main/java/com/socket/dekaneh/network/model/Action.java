package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Action implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("target")
    @Expose
    private Integer target;
    @SerializedName("productIds")
    @Expose
    private List<Object> productIds = null;
    private final static long serialVersionUID = 5064812285282338153L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public List<Object> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Object> productIds) {
        this.productIds = productIds;
    }

}
