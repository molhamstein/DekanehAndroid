package brain_socket.com.dekaneh.network.model;

public class Product {

    private String price;
    private boolean hasOffer;

    public Product(String price, boolean hasOffer) {
        this.price = price;
        this.hasOffer = hasOffer;
    }

    public Product(String price) {
        this.price = price;
        this.hasOffer = false;
    }

    public boolean isHasOffer() {
        return hasOffer;
    }

    public void setHasOffer(boolean hasOffer) {
        this.hasOffer = hasOffer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
