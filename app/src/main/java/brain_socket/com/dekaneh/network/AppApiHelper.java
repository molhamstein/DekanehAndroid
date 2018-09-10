package brain_socket.com.dekaneh.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Singleton;

import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import io.reactivex.Single;

public class AppApiHelper {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

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
        return Rx2AndroidNetworking.get(ApiEndPoint.CATEGORIES)
                .build()
                .getObjectListSingle(Category.class);
    }

    public static Single<List<Offer>> getFeaturedOffers() {
        return Rx2AndroidNetworking.get(ApiEndPoint.FEATURED_OFFERS)
                .build()
                .getObjectListSingle(Offer.class);
    }

}