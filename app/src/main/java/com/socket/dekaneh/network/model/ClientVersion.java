
package com.socket.dekaneh.network.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.socket.dekaneh.network.model.Data;

public class ClientVersion implements Serializable {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = 4730593442251395719L;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

