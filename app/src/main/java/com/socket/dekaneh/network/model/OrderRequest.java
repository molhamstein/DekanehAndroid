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
    @SerializedName("couponId")
    @Expose
    private String couponId;

    public OrderRequest(String clientId, List<Orderitem> orderitems, Coupon coupon) {
        this.clientId = clientId;
        this.products = orderitems;
        if (coupon != null) {
            this.code = coupon.getCode();
            this.couponId = coupon.getId();
        }
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "clientId='" + clientId + '\'' +
                ", products=" + products +
                '}';
    }
}
