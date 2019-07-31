package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("clientCurrentVersion")
    @Expose
    private String clientCurrentVersion;
    @SerializedName("clientMinimumVersion")
    @Expose
    private String clientMinimumVersion;
    @SerializedName("running")
    @Expose
    private Boolean running;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = -8947928022017342124L;

    public String getClientCurrentVersion() {
        return clientCurrentVersion;
    }

    public void setClientCurrentVersion(String clientCurrentVersion) {
        this.clientCurrentVersion = clientCurrentVersion;
    }

    public String getClientMinimumVersion() {
        return clientMinimumVersion;
    }

    public void setClientMinimumVersion(String clientMinimumVersion) {
        this.clientMinimumVersion = clientMinimumVersion;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
