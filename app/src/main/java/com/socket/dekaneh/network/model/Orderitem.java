package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Orderitem implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("productId")
    @Expose
    private String productId;

    public Orderitem(int count, String productId) {
        this.count = count;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "count=" + count +
                ", productId='" + productId + '\'' +
                '}';
    }
}
