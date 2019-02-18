package com.socket.dekaneh.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.utils.GsonUtils;

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
    // TODO should not convert the string to JSON every time !!!!!
    public List<CartItem> getCartItems() {
        List<CartItem> items = GsonUtils.convertJsonStringToCartItemsArray(getPreference().getString(CART_ITEMS, null));
        if (items != null)
            return items;
        return new ArrayList<>();
    }

    public CartItem getCartItemByProdId(String prodId) {
        List<CartItem> items = GsonUtils.convertJsonStringToCartItemsArray(getPreference().getString(CART_ITEMS, null));
        if (items != null) {
            for (CartItem item : items) {
                if (item.getId().equals(prodId)) {
                    return item;
                }
            }
        }
        return null;
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
                item.addOne();
                updateCartItem(item, index);
                itemExist = true;
                break;
            }
            ++index;
        }

        if (!itemExist) {
            mItem.setCount(1);
            cacheNewCartItem(mItem);
        }


    }

    public void removeCartItem(CartItem mItem) {
        int index = 0;
        for (CartItem item : getCartItems()) {
            if (item.getId().equals(mItem.getId())) {
                item.removeOne();
                updateCartItem(item, index);
                if (item.getCount() <= 0) {
                    List<CartItem> items = getCartItems();
                    items.remove(index);
                    cacheCartItems(items);
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
        return 0;
    }

    public boolean isCartItemExist(CartItem mItem) {
        for (CartItem item : getCartItems()) {
            if (item.getId().equals(mItem.getId())) {
                return true;
            }
        }
        return false;
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

    public void clearProductsCache() {
        getPreference().edit().remove(PRODUCTS_LIST).apply();
        getPreference().edit().remove(HOME_CATEGORIES_LIST).apply();
        getPreference().edit().remove(FEATURED_OFFERS).apply();
        getPreference().edit().remove(HOME_CATEGORIES_LIST).apply();

    }

    public void clearCacheWithoutCart() {
        getPreference().edit().remove(PRODUCTS_LIST).apply();
        getPreference().edit().remove(CATEGORIES_LIST).apply();
        getPreference().edit().remove(HOME_CATEGORIES_LIST).apply();
        getPreference().edit().remove(FEATURED_OFFERS).apply();
//        getPreference().edit().remove(CART_ITEMS).apply();
    }
}