package brain_socket.com.dekaneh.network;

import brain_socket.com.dekaneh.BuildConfig;

public class ApiEndPoint {

    private ApiEndPoint(){ }

    public static final String LOGIN = BuildConfig.BASE_API_URL + "users/login";
    public static final String HOME_CATEGORIES = BuildConfig.BASE_API_URL + "products/groupedByCategories";

}
