package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable {

    @Expose
    @SerializedName("id")
    private
    String id;
    @Expose
    @SerializedName("value")
    private
    int value;
    @Expose
    @SerializedName("code")
    private
    String code;
    @SerializedName("numberOfTimes")
    private
    int numberOfTimes;
    @SerializedName("numberOfUsed")
    private
    int numberOfUsed;
    @SerializedName("expireDate")
    private
    Date expireDate;
    @SerializedName("type")
    private
    Type type;


    public String getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    public int getNumberOfUsed() {
        return numberOfUsed;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        fixed, percent
    }
}
