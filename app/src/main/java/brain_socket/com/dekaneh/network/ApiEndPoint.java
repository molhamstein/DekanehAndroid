package brain_socket.com.dekaneh.network;

import brain_socket.com.dekaneh.BuildConfig;

public class ApiEndPoint {

    public static final String PRODUCTS = BuildConfig.BASE_API_URL + "products";
    public static final String USERS = BuildConfig.BASE_API_URL + "users";

    private ApiEndPoint(){ }



    public static final String SIMILAR_PRODUCTS = BuildConfig.BASE_API_URL + "products/similarProduct";
    public static final String LOGIN = BuildConfig.BASE_API_URL + "users/login";
    public static final String HOME_CATEGORIES = BuildConfig.BASE_API_URL + "products/groupedByCategories";
    public static final String CATEGORIES = BuildConfig.BASE_API_URL + "categories";
    public static final String FEATURED_OFFERS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"and\":[{\"isOffer\":\"true\"}, {\"isFeatured\":\"true\"}]}}";
    public static final String OFFERS = BuildConfig.BASE_API_URL + "products?filter={\"where\":{\"and\":[{\"isOffer\":\"true\"}]}}";
    public static final String ORDERS = BuildConfig.BASE_API_URL + "orders";
    public static final String TOP_SLIDERS = BuildConfig.BASE_API_URL + "topSliders";
    public static final String GROUPED_BY_MANUFACTURERS = BuildConfig.BASE_API_URL + "products/groupedByManufacturers";
    public static final String SUB_CATEGORIES = CATEGORIES + "/{id}/subCategories";
    public static final String LOGOUT = "users/logout";

}
