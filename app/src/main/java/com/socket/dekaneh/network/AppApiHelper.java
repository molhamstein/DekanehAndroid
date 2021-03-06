package com.socket.dekaneh.network;

import android.text.format.DateUtils;

import com.google.gson.JsonObject;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.Calendar;
import java.util.List;

import com.socket.dekaneh.Rating;
import com.socket.dekaneh.network.model.Area;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.Favorite;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.LoginRequest;
import com.socket.dekaneh.network.model.LoginResponse;
import com.socket.dekaneh.network.model.Manufacturer;
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
import com.socket.dekaneh.utils.AppDateUtils;

import io.reactivex.Single;

public class AppApiHelper {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;
    public static final int TOTAL_PRICE_IS_LOW_ERROR = 602;
    public static final int PRODUCT_NOT_AVAILABLE_ERROR = 611;
    public static final int ALREADY_IN_FAVORITE_ERROR = 600;
    public static final int FAVORITE_NOT_SET_ERROR = 601;
    public static final int COUPON_NOT_FOUND_ERROR = 605;
    public static final int COUPON_IN_USE = 606;
    public static final int WRONG_CREDENTIALS = 401;

    public static Single<LoginResponse> login(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN)
                .addBodyParameter(request)
                .addQueryParameter("include", "user")
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    public static Single<List<HomeCategory>> getHomeCategories(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.HOME_CATEGORIES)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(HomeCategory.class);
    }

    public static Single<List<Category>> getCategories(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ONLY_CATEGORIES)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Category.class);
    }

    public static Single<List<Offer>> getFeaturedOffers(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.FEATURED_OFFERS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<List<Product>> getFeaturedProducts(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.FEATURED_PRODUCTS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<List<Offer>> getOffers(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.OFFERS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<Order> sendOrder(String accessToken, OrderRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ORDERS)
                .addApplicationJsonBody(request)
//                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Order>> getCurrentOrders(String userId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CURRENT_ORDERS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .addPathParameter("clientId", userId)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<List<Order>> getPastOrders(String userId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PAST_ORDERS)
                .addPathParameter("clientId", userId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<List<SliderImage>> getSliderImages(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.TOP_SLIDERS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(SliderImage.class);
    }

    public static Single<List<Product>> getSimilarProducts(String productId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SIMILAR_PRODUCTS)
                .addQueryParameter("productId", productId)
                .addQueryParameter("limit", "10")
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<List<Manufacturer>> getCategoryManufacturers(String categoryId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GROUPED_BY_MANUFACTURERS)
                .addQueryParameter("categoryId", categoryId)
                .addQueryParameter("limit", "10")
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Manufacturer.class);
    }

    public static Single<List<Manufacturer>> getCategoryManufacturers(String categoryId, String subCategoryId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GROUPED_BY_MANUFACTURERS)
                .addQueryParameter("categoryId", categoryId)
                .addQueryParameter("subCategoryId", subCategoryId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("limit", "10")
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Manufacturer.class);
    }

    public static Single<List<SubCategory>> getSubCategories(String categoryId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SUB_CATEGORIES)
                .addPathParameter("id", categoryId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(SubCategory.class);
    }

//    public static Maybe<String> logout(String accessToken) {
//        return Rx2AndroidNetworking.post(ApiEndPoint.LOGOUT)
//                .addQueryParameter("access_token", accessToken)
//                .build()
//                .getStringMaybe();
//    }

    public static Single<List<Product>> getProductsByManufacturer(String manufacturerId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PRODUCTS_MANUFACTURER)
                .addQueryParameter("manufacturerId", manufacturerId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<User> signUp(SignUpRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.USERS)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(User.class);
    }

    public static Single<Order> patchOrder(String accessToken, OrderRequest order, String orderId) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.ORDER)
                .addPathParameter("id", orderId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .addApplicationJsonBody(order)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<JsonObject> deleteOrder(String accessToken, String orderId) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CANCEL_ORDER)
                .addPathParameter("id", orderId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(JsonObject.class);
    }

    public static Single<Order> getOrderDetails(String orderId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ORDER)
                .addQueryParameter("filter", "{\"include\":[\"coupon\"]}")
                .addPathParameter("id", orderId)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Notification>> getNotifications(String accessToken) {
        return Rx2AndroidNetworking.post(ApiEndPoint.NOTIFICATIONS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Notification.class);
    }

    public static Single<User> patchUser(User user, String accessToken) {
        return Rx2AndroidNetworking.put(ApiEndPoint.EDIT_USERS)
                .addApplicationJsonBody(user)
                .addPathParameter("id", user.getId())
                .build()
                .getObjectSingle(User.class);
    }

    public static Single<Product> getProduct(Product product, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SINGLE_PRODUCTS)
                .addPathParameter("id", product.getId())
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Product.class);
    }

    public static Single<List<Offer>> getProductOffers(String productId, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PRODUCT_OFFERS)
                .addPathParameter("id", productId)
                .addQueryParameter("limit", "10")
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<List<Product>> search(String query, String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SEARCH)
                .addQueryParameter("string", query)
                .addHeaders("Authorization", accessToken)
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
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .addBodyParameter("rate", rate.toString())
                .addBodyParameter("userId", userId)
                .addBodyParameter("orderId", orderId)
                .build()
                .getObjectSingle(JsonObject.class);
    }


    public static Single<Boolean> isActivated(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CHECK_STATUS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Boolean.class);
    }


    public static Single<List<Coupon>> getCoupons(String accessToken, String userId) {
        Calendar calendar = Calendar.getInstance();

        return Rx2AndroidNetworking.get(ApiEndPoint.COUPONS)
                .addPathParameter("user_id", userId)
                .addPathParameter("dateNow", AppDateUtils.gettUtcTimeString(calendar.getTime()) )
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Coupon.class);
    }

    public static Single<String> putFirebaseToken(String accessToken, String firebaseToken) {
        return Rx2AndroidNetworking.put(ApiEndPoint.SET_FIREBASE_TOKEN)
                .addHeaders("Authorization", accessToken)
                .addBodyParameter("token", firebaseToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(String.class);
    }


    public static Single<Favorite> setFavorite(String accessToken, String productId) {
        return Rx2AndroidNetworking.post(ApiEndPoint.FAVORITE)
                .addHeaders("Authorization", accessToken)
                .addBodyParameter("productId", productId)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Favorite.class);
    }

    public static Single<String> deleteFavorite(String accessToken, String productId) {
        return Rx2AndroidNetworking.delete(ApiEndPoint.DELETE_FAVORITE)
                .addHeaders("Authorization", accessToken)
                .addBodyParameter("productId", productId)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(String.class);
    }

    public static Single<List<Product>> getFavorites(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.FAVORITE_PRODUCTS)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Product.class);
    }

    public static Single<String> forgetPassword(String phoneNumber) {
        return Rx2AndroidNetworking.post(ApiEndPoint.FORGET_PASSWORD)
                .addBodyParameter("phoneNumber", phoneNumber)
                .build()
                .getObjectSingle(String.class);
    }

    public static Single<String> forgetPasswordNotifyAdmin(String phoneNumber) {
        return Rx2AndroidNetworking.post(ApiEndPoint.FORGOT_PASSWORD_NOTIFY_ADMIN)
                .addBodyParameter("phoneNumber", phoneNumber)
                .build()
                .getObjectSingle(String.class);
    }



    public static Single<Coupon> addCoupon(String accessToken, String couponCode) {
        return Rx2AndroidNetworking.put(ApiEndPoint.ADD_COUPON)
                .addBodyParameter("code", couponCode)
                .addHeaders("Authorization", accessToken)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Coupon.class);
    }

    public static Single<Manufacturer> fetchManufacturer(String id) {
        return Rx2AndroidNetworking.put(ApiEndPoint.MANUFACTURER)
                .addPathParameter("id", id)
                .build()
                .getObjectSingle(Manufacturer.class);
    }



}