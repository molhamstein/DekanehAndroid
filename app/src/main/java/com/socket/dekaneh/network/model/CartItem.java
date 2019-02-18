package com.socket.dekaneh.network.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartItem extends Product implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("productId")
    @Expose
    private String productId;


    public CartItem(int count, Product product) {
        super(product);
        this.count = 0;
    }

    public CartItem(Product product) {
        super(product);
    }

    public CartItem(Offer offer) {
        super(offer, offer.getMedia());
    }


    @Override
    public String getId() {
        return super.getId();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public void addOne() {
        //Log.d(TAG, "addOne: " + getOfferMaxQuantity());
        if (getOfferMaxQuantity() > count)
            ++count;
    }

    public void removeOne() {
        --count;
    }

    public int getTotalPrice(boolean isHoreca) {
        if (isHoreca) return getPrice(isHoreca) * getCount();
        else return getPrice(isHoreca) * getCount();
    }

    public String getProductId() {
        return productId;
    }

    public int getTotalPrice() {
        return price * count;
    }

}
