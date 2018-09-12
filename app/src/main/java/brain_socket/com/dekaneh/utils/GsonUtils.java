package brain_socket.com.dekaneh.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;


public class GsonUtils {

    public static <T> String convertArrayToJsonString(List<T> items){
        return new Gson().toJson(items);
    }

    public static List<Category> convertJsonStringToCategoriesArray(String json){
        return new Gson().fromJson(json, new TypeToken<List<Category>>(){}.getType());
    }

    public static List<Product> convertJsonStringToProductsArray(String json){
        return new Gson().fromJson(json, new TypeToken<List<Product>>(){}.getType());
    }

    public static List<HomeCategory> convertJsonStringToHomeCategoriesArray(String json) {
        return new Gson().fromJson(json, new TypeToken<List<HomeCategory>>(){}.getType());
    }

    public static List<Offer> convertJsonStringToOffersArray(String json) {
        return new Gson().fromJson(json, new TypeToken<List<Offer>>(){}.getType());
    }

    public static <T> String convertObjectToJson(T item) {
        return new Gson().toJson(item);
    }

    public static Product convertJsonStringToProductObject(String json) {
        return new Gson().fromJson(json, new TypeToken<Product>(){}.getType());
    }
}