package com.socket.dekaneh.network;

import com.google.gson.JsonObject;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import com.socket.dekaneh.Rating;
import com.socket.dekaneh.network.model.Area;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.LoginRequest;
import com.socket.dekaneh.network.model.LoginResponse;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.network.model.ManufacturerProduct;
import com.socket.dekaneh.network.model.Notification;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.network.model.OrderRequest;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.SignUpRequest;
import com.socket.dekaneh.network.model.SliderImage;
import com.socket.dekaneh.network.model.SubCategory;
import com.socket.dekaneh.network.model.User;

import com.socket.dekaneh.network.model.Coupon;

import io.reactivex.Single;

public class AppApiHelper {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;
    public static final int TOTAL_PRICE_IS_LOW_ERROR = 602;

    public static Single<LoginResponse> login(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN)
                .addBodyParameter(request)
                .addQueryParameter("include", "user")
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    public static Single<List<HomeCategory>> getHomeCategories() {
        return Rx2AndroidNetworking.get(ApiEndPoint.HOME_CATEGORIES)
                .build()
                .getObjectListSingle(HomeCategory.class);
    }

    public static Single<List<Category>> getCategories() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ONLY_CATEGORIES)
                .build()
                .getObjectListSingle(Category.class);
    }

    public static Single<List<Offer>> getFeaturedOffers() {
        return Rx2AndroidNetworking.get(ApiEndPoint.FEATURED_OFFERS)
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<List<Product>> getFeaturedProducts() {
        return Rx2AndroidNetworking.get(ApiEndPoint.FEATURED_PRODUCTS)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<List<Offer>> getOffers() {
        return Rx2AndroidNetworking.get(ApiEndPoint.OFFERS)
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<Order> sendOrder(String accessToken, OrderRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ORDERS)
                .addApplicationJsonBody(request)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Order>> getCurrentOrders(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CURRENT_ORDERS)
                .addPathParameter("clientId", userId)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<List<Order>> getPastOrders(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PAST_ORDERS)
                .addPathParameter("clientId", userId)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<List<SliderImage>> getSliderImages() {
        return Rx2AndroidNetworking.get(ApiEndPoint.TOP_SLIDERS)
                .build()
                .getObjectListSingle(SliderImage.class);
    }

    public static Single<List<Product>> getSimilarProducts(String productId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SIMILAR_PRODUCTS)
                .addQueryParameter("productId", productId)
                .addQueryParameter("limit", "10")
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<List<Manufacturer>> getCategoryManufacturers(String categoryId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GROUPED_BY_MANUFACTURERS)
                .addQueryParameter("categoryId", categoryId)
                .addQueryParameter("limit", "10")
                .build()
                .getObjectListSingle(Manufacturer.class);
    }

    public static Single<List<Manufacturer>> getCategoryManufacturers(String categoryId, String subCategoryId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GROUPED_BY_MANUFACTURERS)
                .addQueryParameter("categoryId", categoryId)
                .addQueryParameter("subCategoryId", subCategoryId)
                .addQueryParameter("limit", "10")
                .build()
                .getObjectListSingle(Manufacturer.class);
    }

    public static Single<List<SubCategory>> getSubCategories(String categoryId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SUB_CATEGORIES)
                .addPathParameter("id", categoryId)
                .build()
                .getObjectListSingle(SubCategory.class);
    }

//    public static Maybe<String> logout(String accessToken) {
//        return Rx2AndroidNetworking.post(ApiEndPoint.LOGOUT)
//                .addQueryParameter("access_token", accessToken)
//                .build()
//                .getStringMaybe();
//    }

    public static Single<List<Product>> getProductsByManufacturer(String manufacturerId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PRODUCTS_RAW)
                .addQueryParameter("filter[where][manufacturerId]", manufacturerId)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<User> signUp(SignUpRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.USERS)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(User.class);
    }

    public static Single<Order> patchOrder(Order order) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.ORDER)
                .addPathParameter("id", order.getId())
                .addApplicationJsonBody(order)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<Order> getOrderDetails(String orderId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ORDER)
                .addPathParameter("id", orderId)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Notification>> getNotifications(String accessToken) {
        return Rx2AndroidNetworking.post(ApiEndPoint.NOTIFICATIONS)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Notification.class);
    }

    public static Single<User> patchUser(User user, String accessToken) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.USERS)
                .addApplicationJsonBody(user)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(User.class);
    }

    public static Single<Product> getProduct(Product product) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SINGLE_PRODUCTS)
                .addPathParameter("id", product.getId())
                .build()
                .getObjectSingle(Product.class);
    }

    public static Single<List<Offer>> getProductOffers(String productId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PRODUCT_OFFERS)
                .addPathParameter("id", productId)
                .addQueryParameter("limit", "10")
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<List<Product>> search(String query, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SEARCH)
                .addQueryParameter("string", query)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<List<Area>> getAreas() {
        return Rx2AndroidNetworking.get(ApiEndPoint.AREAS)
                .build()
                .getObjectListSingle(Area.class);
    }

    public static Single<JsonObject> postRating(String accessToken, Rating.Rate rate, String userId, String orderId) {
        return Rx2AndroidNetworking.post(ApiEndPoint.RATINGS)
                .addQueryParameter("access_token", accessToken)
                .addBodyParameter("rate", rate.toString())
                .addBodyParameter("userId", userId)
                .addBodyParameter("orderId", orderId)
                .build()
                .getObjectSingle(JsonObject.class);
    }


    public static Single<Boolean> isActivated(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CHECK_STATUS)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Boolean.class);
    }


    public static Single<List<Coupon>> getCoupons(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.COUPONS)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Coupon.class);
    }

}