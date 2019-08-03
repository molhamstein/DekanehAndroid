package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    public static final String TAG = Order.class.getSimpleName();
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("orderDate")
    @Expose
    private Date orderDate;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("totalPrice")
    @Expose
    private int totalPrice;
    @SerializedName("priceBeforeCoupon")
    @Expose
    private int priceBeforeCoupon;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("assignedDate")
    @Expose
    private Date assignedDate;
    @SerializedName("deliveredDate")
    @Expose
    private Date deliveredDate;
    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("deliveryMemberId")
    @Expose
    private String deliveryMemberId;
    @SerializedName("couponId")
    @Expose
    private String couponId;
    @SerializedName("orderProducts")
    @Expose
    private List<CartItem> products;
    @SerializedName("coupon")
    @Expose
    private Coupon coupon;
    @SerializedName("awards")
    @Expose
    private List<Award> awards;

    @SerializedName("orderPrizes")
    @Expose
    private List<OrderPrize> orderPrizes;


    public Order(int totalPrice, String clientId, List<CartItem> items) {
        this.clientId = clientId;
        this.products = items;
//        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getClientType() {
        return clientType;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public String getClientId() {
        return clientId;
    }

    public String getDeliveryMemberId() {
        return deliveryMemberId;
    }

    public String getCouponId() {
        return couponId;
    }

    public List<CartItem> getItems() {
        return products;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public int getPriceBeforeCoupon() {
        return priceBeforeCoupon;
    }

    public boolean isCouponExist() {
        return priceBeforeCoupon != totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", clientType='" + clientType + '\'' +
                ", assignedDate=" + assignedDate +
                ", deliveredDate=" + deliveredDate +
                ", clientId='" + clientId + '\'' +
                ", deliveryMemberId='" + deliveryMemberId + '\'' +
                ", couponId='" + couponId + '\'' +
                ", products=" + products +
                '}';
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public List<OrderPrize> getOrderPrizes() {
        return orderPrizes;
    }

    public void setOrderPrizes(List<OrderPrize> orderPrizes) {
        this.orderPrizes = orderPrizes;
    }

    public enum Status {
        pending, inDelivery, delivered, canceled
    }
}
