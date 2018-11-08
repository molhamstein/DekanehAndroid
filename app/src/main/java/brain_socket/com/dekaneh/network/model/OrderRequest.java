package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {

    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("products")
    @Expose
    private List<CartItem> products;

    public OrderRequest(String clientId, List<CartItem> products) {
        this.clientId = clientId;
        this.products = products;
    }
}
