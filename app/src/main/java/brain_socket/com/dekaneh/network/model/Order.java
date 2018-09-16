package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("orderDate")
    @Expose
    private Date orderDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalPrice")
    @Expose
    private double totalPrice;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("assignedDate")
    @Expose
    private Date assignedDate;
    @SerializedName("deliveredDate")
    @Expose
    private Date deliveredDate;
    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("deliveryMemberId")
    @Expose
    private String deliveryMemberId;
    @SerializedName("couponId")
    @Expose
    private String couponId;
    @SerializedName("products")
    @Expose
    private List<Item> items;


    public class Item extends Product implements Serializable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("count")
        @Expose
        private int count;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("creationDate")
        @Expose
        private Date creationDate;
        @SerializedName("productId")
        @Expose
        private String productId;

        public Item(Product product) {
            super(product);
        }


        public String getId() {
            return id;
        }

        public int getCount() {
            return count;
        }

        public String getPrice() {
            return price;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public String getProductId() {
            return productId;
        }
    }

}
