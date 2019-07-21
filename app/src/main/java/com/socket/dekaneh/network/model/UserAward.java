package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserAward implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("progress")
    @Expose
    private Integer progress;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("complete")
    @Expose
    private Boolean complete;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("awardId")
    @Expose
    private String awardId;
    @SerializedName("awardPeriodId")
    @Expose
    private String awardPeriodId;
    private final static long serialVersionUID = -4748134076745299724L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardPeriodId() {
        return awardPeriodId;
    }

    public void setAwardPeriodId(String awardPeriodId) {
        this.awardPeriodId = awardPeriodId;
    }

}
