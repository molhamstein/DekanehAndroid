package brain_socket.com.dekaneh.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Order;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.GsonUtils;

@Singleton
public class CacheStore {

    private Context context;
    private Session session;

    @Inject
    public CacheStore(Context context, Session session) {
        this.context = context;
        this.session = session;
    }


    private static final String PRODUCTS_LIST = "products_list";
    private static final String CATEGORIES_LIST = "categories_list";
    private static final String HOME_CATEGORIES_LIST = "home_categories_list";
    private static final String FEATURED_OFFERS = "featured_offers_list";
    private static final String CART_ITEMS = "cart_items";

    public Session getSession() {
        return session;
    }

    public void cacheProducts(List<Product> products) {
        getPreference()
                .edit()
                .putString(PRODUCTS_LIST, GsonUtils.convertArrayToJsonString(products))
                .apply();
    }

    public List<Product> getProducts() {
        return GsonUtils.convertJsonStringToProductsArray(getPreference().getString(PRODUCTS_LIST, null));
    }

    public void cacheCategories(List<Category> categories) {
        getPreference()
                .edit()
                .putString(CATEGORIES_LIST, GsonUtils.convertArrayToJsonString(categories))
                .apply();
    }

    public List<Category> getCategories() {
        return GsonUtils.convertJsonStringToCategoriesArray(getPreference().getString(CATEGORIES_LIST, null));
    }

    public void cacheHomeCategories(List<HomeCategory> homeCategories) {
        getPreference()
                .edit()
                .putString(HOME_CATEGORIES_LIST, GsonUtils.convertArrayToJsonString(homeCategories))
                .apply();
    }

    public List<HomeCategory> getHomeCategories() {
        return GsonUtils.convertJsonStringToHomeCategoriesArray(getPreference().getString(HOME_CATEGORIES_LIST, null));
    }

    public void cacheFeaturedOffers(List<Offer> offers) {
        getPreference()
                .edit()
                .putString(FEATURED_OFFERS, GsonUtils.convertArrayToJsonString(offers))
                .apply();
    }

    public List<Offer> getFeaturedOffers() {
        return GsonUtils.convertJsonStringToOffersArray(getPreference().getString(FEATURED_OFFERS, null));
    }

    public void cacheCartItems(List<CartItem> items) {
        getPreference()
                .edit()
                .putString(CART_ITEMS, GsonUtils.convertArrayToJsonString(items))
                .apply();
    }

    public List<CartItem> getCartItems() {
        List<CartItem> items = GsonUtils.convertJsonStringToCartItemsArray(getPreference().getString(CART_ITEMS, null));
        if (items != null)
            return items;
        return new ArrayList<>();
    }

    private void cacheNewCartItem(CartItem item) {
        List<CartItem> items = getCartItems();
        items.add(item);
        cacheCartItems(items);
    }

    private void updateCartItem(CartItem item, int index) {
        List<CartItem> items = getCartItems();
        items.set(index, item);
        cacheCartItems(items);
    }

    public void addCartItem(CartItem mItem) {
        boolean itemExist = false;
        int index = 0;
        for (CartItem item : getCartItems()) {
            if (item.getId().equals(mItem.getId())) {
                Log.d("XXX", "addCartItem: inside if " + item.getCount());
                item.addOne();
                updateCartItem(item, index);
                itemExist = true;
                break;
            }
            ++index;
            Log.d("XXX", "addCartItem: inside for " + item.getCount());
        }

        if (!itemExist) {
            mItem.setCount(1);
            Log.d("XXX", "addCartItem: outside " + mItem.getCount());
            cacheNewCartItem(mItem);
        }


    }

    public void removeCartItem(CartItem mItem) {
        int index = 0;
        for (CartItem item : getCartItems()) {
            if (item.getId().equals(mItem.getId())) {
                item.removeOne();
                updateCartItem(item, index);
                if (item.getCount() == 0) {
                    getCartItems().remove(item);
                    cacheCartItems(getCartItems());
                }
                break;
            }
            ++index;
        }
    }

    public int cartItemCount(CartItem mItem) {
        for (CartItem item : getCartItems()) {
            if (item.getId().equals(mItem.getId())) {
                return item.getCount();
            }
        }
        return 1;
    }

    public void clearCart() {
        getPreference()
                .edit()
                .putString(CART_ITEMS, "")
                .apply();
    }


    private SharedPreferences getPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void clearCache() {
        getPreference().edit().clear().apply();
    }
}