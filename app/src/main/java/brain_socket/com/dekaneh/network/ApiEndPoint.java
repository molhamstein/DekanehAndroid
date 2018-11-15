package brain_socket.com.dekaneh.network;

import brain_socket.com.dekaneh.BuildConfig;

public class ApiEndPoint {

    private ApiEndPoint(){ }

    public static final String PRODUCTS = BuildConfig.BASE_API_URL + "products";
    public static final String SINGLE_PRODUCTS = BuildConfig.BASE_API_URL + "products/{id}";
    public static final String USERS = BuildConfig.BASE_API_URL + "users";
    public static final String SIMILAR_PRODUCTS = BuildConfig.BASE_API_URL + "products/similarProduct";
    public static final String LOGIN = BuildConfig.BASE_API_URL + "users/login";
    public static final String HOME_CATEGORIES = BuildConfig.BASE_API_URL + "products/groupedByCategories";
    public static final String ONLY_CATEGORIES = BuildConfig.BASE_API_URL + "categories?filter={\"where\":{\"parentCategoryId\" : {\"exists\" : false}},\"include\":\"subCategories\"}";
    public static final String CATEGORIES = BuildConfig.BASE_API_URL + "categories";
    public static final String FEATURED_OFFERS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"and\":[{\"isOffer\":\"true\"}, {\"isFeatured\":\"true\"}]}}";
    public static final String OFFERS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"and\":[{\"isOffer\":\"true\"}]}}";
    public static final String ORDERS = BuildConfig.BASE_API_URL + "orders";
    public static final String CURRENT_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"},{\"clientId\":\"{clientId}\"}]}}";
    public static final String PAST_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"delivered\"},{\"clientId\":\"{clientId}\"}]}}";
    public static final String TOP_SLIDERS = BuildConfig.BASE_API_URL + "topSliders";
    public static final String GROUPED_BY_MANUFACTURERS = BuildConfig.BASE_API_URL + "products/groupedByManufacturers";
    public static final String SUB_CATEGORIES = CATEGORIES + "/{id}/subCategories";
    public static final String ORDER = ORDERS + "/{id}";
    public static final String NOTIFICATIONS = BuildConfig.BASE_API_URL + "notifications";
    public static final String LOGOUT = "users/logout";
    public static final String PRODUCT_OFFERS = PRODUCTS + "/{id}/offers" ;
    public static final String SEARCH = PRODUCTS + "/search" ;
    public static final String AREAS = BuildConfig.BASE_API_URL + "areas";
    public static final String RATINGS = BuildConfig.BASE_API_URL + "ratings";
    public static final String COUPONS = BuildConfig.BASE_API_URL + "coupons";



}
