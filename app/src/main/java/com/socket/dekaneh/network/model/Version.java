
package com.socket.dekaneh.network.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version implements Serializable {

    @SerializedName("version")
    @Expose
    private String version;
    private final static long serialVersionUID = 6473807742239519172L;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}