package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomeCategory extends Category implements Serializable {

    @SerializedName("products")
    @Expose
    private List <Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
