package com.socket.dekaneh.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.network.model.Product;


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

    public static Category convertJsonStringToCategoryObject(String json) {
        return new Gson().fromJson(json, new TypeToken<Category>(){}.getType());
    }

    public static Manufacturer convertJsonStringToManufacturerObject(String json) {
        return new Gson().fromJson(json, new TypeToken<Manufacturer>(){}.getType());
    }

    public static Order convertJsonStringToOrderObject(String json) {
        return new Gson().fromJson(json, new TypeToken<Order>(){}.getType());
    }

    public static List<CartItem> convertJsonStringToCartItemsArray(String json) {
        if (json == null) {
            return new ArrayList<>();
        }
        return new Gson().fromJson(json, new TypeToken<List<CartItem>>(){}.getType());
    }
}
