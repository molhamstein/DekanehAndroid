package com.socket.dekaneh.network;

import com.socket.dekaneh.BuildConfig;

class ApiEndPoint {

    private ApiEndPoint(){ }

    static final String PRODUCTS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"status\":\"available\"}}";
    static final String PRODUCTS_RAW = BuildConfig.BASE_API_URL + "products";
    static final String PRODUCTS_MANUFACTURER = BuildConfig.BASE_API_URL + "products/productsManufacturer";
    static final String SINGLE_PRODUCTS = BuildConfig.BASE_API_URL + "products/{id}";
    static final String USERS = BuildConfig.BASE_API_URL + "users";
    static final String SIMILAR_PRODUCTS = BuildConfig.BASE_API_URL + "products/similarProduct";
    static final String LOGIN = BuildConfig.BASE_API_URL + "users/login";
    static final String HOME_CATEGORIES = BuildConfig.BASE_API_URL + "products/groupedByCategories";
    static final String ONLY_CATEGORIES = BuildConfig.BASE_API_URL + "categories?filter={\"where\":{\"parentCategoryId\" : {\"exists\" : false}},\"include\":\"subCategories\"}";
    static final String CATEGORIES = BuildConfig.BASE_API_URL + "categories";
    static final String FEATURED_OFFERS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"and\":[{\"isOffer\":\"true\"}, {\"isFeatured\":\"true\"}]}}";
    static final String FEATURED_PRODUCTS = BuildConfig.BASE_API_URL + "products/productsFeatured";
    static final String OFFERS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"and\":[{\"isOffer\":\"true\"}]}}";
    static final String ORDERS = BuildConfig.BASE_API_URL + "orders";
    static final String CURRENT_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"},{\"clientId\":\"{clientId}\"}]}}";
    static final String PAST_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"delivered\"},{\"clientId\":\"{clientId}\"}]}}";
    static final String TOP_SLIDERS = BuildConfig.BASE_API_URL + "topSliders?filter[where][status]=activated";
    static final String GROUPED_BY_MANUFACTURERS = BuildConfig.BASE_API_URL + "products/groupedByManufacturers";
    static final String SUB_CATEGORIES = CATEGORIES + "/{id}/subCategories";
    static final String ORDER = ORDERS + "/{id}";
    static final String NOTIFICATIONS = BuildConfig.BASE_API_URL + "notifications";
    static final String LOGOUT = "users/logout";
    static final String PRODUCT_OFFERS = PRODUCTS + "/{id}/offers" ;
    static final String SEARCH = PRODUCTS_RAW + "/searchClient" ;
    static final String AREAS = BuildConfig.BASE_API_URL + "areas";
    static final String RATINGS = BuildConfig.BASE_API_URL + "ratings";
    static final String COUPONS = BuildConfig.BASE_API_URL + "coupons";
    static final String CHECK_STATUS = USERS + "/isActivated";
    static final String SET_FIREBASE_TOKEN = USERS + "/setFirebaseToken";
    static final String FAVORITE = BuildConfig.BASE_API_URL + "/favorite";
    static final String DELETE_FAVORITE = FAVORITE + "/deleteFavorite";
    static final String FAVORITE_PRODUCTS = FAVORITE + "/getFavorite";
    static final String FORGET_PASSWORD = USERS + "/forgetPassword";

}
