package com.socket.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Favorite implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("productsId")
    @Expose
    private String productsId;

}
