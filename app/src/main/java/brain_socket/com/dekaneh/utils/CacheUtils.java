package brain_socket.com.dekaneh.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Product;


public class CacheUtils {

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
}
