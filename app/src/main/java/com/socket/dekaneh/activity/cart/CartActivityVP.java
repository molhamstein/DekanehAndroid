package brain_socket.com.dekaneh.activity.cart;

import java.util.List;

import com.socket.dekaneh.base.BasePresenter;
import com.socket.dekaneh.base.BaseView;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Coupon;

public class CartActivityVP {

    public interface View extends BaseView {
        void addAllItems(List<CartItem> items);
        void setOrderViewClear(boolean clear);
        void setOkResult();
        void disableOrderBtn();
        void addAllCoupons(List<Coupon> coupons);
        void updatePriceAfterCoupon(int couponValue, boolean isFixed);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void fetchItems();
        void sendOrder();
        int getPrice();
        void getCoupons();
        void setCoupon(Coupon coupon);
        int getSubtotalPrice();
        void setNote(String note);
        void getCoupon(String couponCode);
    }
}
