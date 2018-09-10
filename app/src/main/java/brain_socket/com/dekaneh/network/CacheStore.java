package brain_socket.com.dekaneh.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.CacheUtils;

@Singleton
public class CacheStore {

    private Context context;
    @Inject
    public CacheStore(Context context) {
        this.context = context;
    }


    private static final String PRODUCTS_LIST = "products_list";
    private static final String CATEGORIES_LIST = "products_list";
    private static final String HOME_CATEGORIES_LIST = "home_categories_list";
    private static final String FEATURED_OFFERS = "home_categories_list";

    public void cacheProducts(List<Product> products) {
        getPreference()
                .edit()
                .putString(PRODUCTS_LIST, CacheUtils.convertArrayToJsonString(products))
                .apply();
    }

    public List<Product> getProducts() {
        return CacheUtils.convertJsonStringToProductsArray(getPreference().getString(PRODUCTS_LIST, null));
    }

    public void cacheCategories(List<Category> categories) {
        getPreference()
                .edit()
                .putString(CATEGORIES_LIST, CacheUtils.convertArrayToJsonString(categories))
                .apply();
    }

    public List<Category> getCategories() {
        return CacheUtils.convertJsonStringToCategoriesArray(getPreference().getString(CATEGORIES_LIST, null));
    }

    public void cacheHomeCategories(List<HomeCategory> homeCategories) {
        getPreference()
                .edit()
                .putString(HOME_CATEGORIES_LIST, CacheUtils.convertArrayToJsonString(homeCategories))
                .apply();
    }

    public List<HomeCategory> getHomeCategories() {
        return CacheUtils.convertJsonStringToHomeCategoriesArray(getPreference().getString(HOME_CATEGORIES_LIST, null));
    }

    public void cacheFeaturedOffers(List<Offer> offers) {
        getPreference()
                .edit()
                .putString(FEATURED_OFFERS, CacheUtils.convertArrayToJsonString(offers))
                .apply();
    }

    public List<Offer> getFeaturedOffers() {
        return CacheUtils.convertJsonStringToOffersArray(getPreference().getString(FEATURED_OFFERS, null));
    }


    private SharedPreferences getPreference(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
