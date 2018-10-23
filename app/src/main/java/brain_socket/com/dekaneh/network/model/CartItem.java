package brain_socket.com.dekaneh.network.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartItem extends Product implements Serializable{

    @SerializedName("count")
    @Expose
    private int count;


    public CartItem(int count, Product product) {
        super(product);
        this.count = 0;
    }

    public CartItem(Product product) {
        super(product);
    }

    public CartItem(Offer offer) {
        super(offer, offer.getProducts().get(0).getImage());
    }



    @Override
    public String getId() {
        return super.getId();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void addOne() {
        ++count;
    }

    public void removeOne() {
        --count;
    }

    public int getWholePrice() {
        return getRetailPrice() * getCount();
    }

}
