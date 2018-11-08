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
    private List<Orderitem> products;

    public OrderRequest(String clientId, List<Orderitem> orderitems) {
        this.clientId = clientId;
        this.products = orderitems;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "clientId='" + clientId + '\'' +
                ", products=" + products +
                '}';
    }
}
