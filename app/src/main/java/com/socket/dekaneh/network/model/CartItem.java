package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartItem extends Product implements Serializable{

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("price")
    @Expose
    private int price;


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

    public int getPrice() {
        return price;
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

    public void addOne() {
        ++count;
    }

    public void removeOne() {
        --count;
    }

    public int getTotalPrice() {
        return price * count;
    }


}
