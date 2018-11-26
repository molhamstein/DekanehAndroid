package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Notification implements Serializable{

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ownerId")
    @Expose
    private String ownerId;
    @SerializedName("actorId")
    @Expose
    private String actorId;

    public String getAction() {
        return action;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getActorId() {
        return actorId;
    }
}
