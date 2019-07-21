package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Coupon implements Serializable {

    @Expose
    @SerializedName("id")
    private
    String id;
    @Expose
    @SerializedName("value")
    private
    Integer value;
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
    String expireDate;
    @SerializedName("type")
    private
    Type type;

    @SerializedName("duration")
    @Expose
    private Integer duration;


    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public Integer getValue() {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        Date formattedDate = null;

        try {
            formattedDate = sdf.parse(expireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        fixed, percent
    }
}
