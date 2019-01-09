package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("clientType")
    @Expose
    private Type clientType;
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("locationPoint")
    @Expose
    private LocationPoint locationPoint;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("hideHistory")
    @Expose
    private boolean hideHistory;


    public User(String id, String phoneNumber, String email, String clientType, String ownerName, String shopName, boolean hideHistory) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.clientType = clientType.equals(Type.horeca.toString()) ? Type.horeca : Type.wholesale;
        this.ownerName = ownerName;
        this.shopName = shopName;
        this.hideHistory = hideHistory;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public Type getClientType() {
        return clientType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getShopName() {
        return shopName;
    }

    public LocationPoint getLocationPoint() {
        return locationPoint;
    }

    public String getNotes() {
        return notes;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public enum Status {
        activated, pending, deactivated
    }

    public boolean isHideHistory() {
        return hideHistory;
    }

    public void setHideHistory(boolean hideHistory) {
        this.hideHistory = hideHistory;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", clientType=" + clientType +
                ", ownerName='" + ownerName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", locationPoint=" + locationPoint +
                ", notes='" + notes + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClientType(Type clientType) {
        this.clientType = clientType;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setLocationPoint(LocationPoint locationPoint) {
        this.locationPoint = locationPoint;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public enum Type {
        wholesale, horeca
    }
}
