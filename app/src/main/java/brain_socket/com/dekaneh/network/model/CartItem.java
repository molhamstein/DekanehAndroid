package brain_socket.com.dekaneh.network.model;

public class CartItem extends Product{

    private int count;

    public CartItem(int count, Product product) {
        super(product);
        this.count = 0;
    }

    public CartItem(Product product) {
        super(product);
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
}
