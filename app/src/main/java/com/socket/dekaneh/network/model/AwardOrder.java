package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AwardOrder implements Serializable
{

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("progressDiff")
    @Expose
    private Integer progressDiff;
    private final static long serialVersionUID = 2464296103253531985L;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getProgressDiff() {
        return progressDiff;
    }

    public void setProgressDiff(Integer progressDiff) {
        this.progressDiff = progressDiff;
    }

}
