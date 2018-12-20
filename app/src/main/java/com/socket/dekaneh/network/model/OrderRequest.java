package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {

    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("orderProducts")
    @Expose
    private List<Orderitem> products;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("couponCode")
    @Expose
    private String couponCode;
    @SerializedName("note")
    @Expose
    private String note;


    public OrderRequest(String clientId, List<Orderitem> orderitems, Coupon coupon, String note) {
        this.clientId = clientId;
        this.products = orderitems;
        this.note = note;
        if (coupon != null) {
            this.code = coupon.getCode();
            this.couponCode = coupon.getCode();
        }
    }

    public OrderRequest(String clientId, List<Orderitem> orderitems) {
        this.note = note;
        this.clientId = clientId;
        this.products = orderitems;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "clientId='" + clientId + '\'' +
                ", products=" + products +
                '}';
    }
}
