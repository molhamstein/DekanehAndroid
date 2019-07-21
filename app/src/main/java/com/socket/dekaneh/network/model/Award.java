package com.socket.dekaneh.network.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Award implements Serializable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("complete")
    @Expose
    private Boolean complete;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("countLimit")
    @Expose
    private Integer countLimit;
    @SerializedName("times")
    @Expose
    private Integer times;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("occurrence")
    @Expose
    private Integer occurrence;
    @SerializedName("occurrenceType")
    @Expose
    private String occurrenceType;
    @SerializedName("clientTypes")
    @Expose
    private List<String> clientTypes = null;
    @SerializedName("areaIds")
    @Expose
    private List<Object> areaIds = null;
    @SerializedName("action")
    @Expose
    private Action action;
    @SerializedName("coupons")
    @Expose
    private List<Coupon> coupons = null;
    @SerializedName("prizes")
    @Expose
    private List<AwardPrize> prizes = null;
    @SerializedName("levelIds")
    @Expose
    private List<Object> levelIds = null;
    @SerializedName("period")
    @Expose
    private AwardPeriod period;
    @SerializedName("userAward")
    @Expose
    private UserAward userAward;
    private final static long serialVersionUID = 2364297477899834745L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountLimit() {
        return countLimit;
    }

    public void setCountLimit(Integer countLimit) {
        this.countLimit = countLimit;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date formattedDate = null;
        try {
            formattedDate = sdf.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  sdf2.format(formattedDate);
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Integer occurrence) {
        this.occurrence = occurrence;
    }

    public String getOccurrenceType() {
        return occurrenceType;
    }

    public void setOccurrenceType(String occurrenceType) {
        this.occurrenceType = occurrenceType;
    }

    public List<String> getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(List<String> clientTypes) {
        this.clientTypes = clientTypes;
    }

    public List<Object> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(List<Object> areaIds) {
        this.areaIds = areaIds;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<AwardPrize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<AwardPrize> prizes) {
        this.prizes = prizes;
    }

    public List<Object> getLevelIds() {
        return levelIds;
    }

    public void setLevelIds(List<Object> levelIds) {
        this.levelIds = levelIds;
    }

    public AwardPeriod getPeriod() {
        return period;
    }

    public void setPeriod(AwardPeriod period) {
        this.period = period;
    }

    public UserAward getUserAward() {
        return userAward;
    }

    public void setUserAward(UserAward userAward) {
        this.userAward = userAward;
    }


    public Integer getProgressPercentage(){
        Integer userProgress = this.userAward.getProgress();
        Integer target = this.action.getTarget() ;
        return (userProgress * 100)/target ;

    }

}



