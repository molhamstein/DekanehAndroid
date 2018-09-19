package brain_socket.com.dekaneh.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Singleton;

import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.LoginRequest;
import brain_socket.com.dekaneh.network.model.LoginResponse;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Order;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.SliderImage;
import brain_socket.com.dekaneh.network.model.SubCategory;
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

    public static Single<List<Offer>> getOffers() {
        return Rx2AndroidNetworking.get(ApiEndPoint.OFFERS)
                .build()
                .getObjectListSingle(Offer.class);
    }

    public static Single<Order> sendOrder(String accessToken, Order request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ORDERS)
                .addApplicationJsonBody(request)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Order>> getOrders(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ORDERS)
                .addQueryParameter("filter[where][clientId]", userId)
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

    public static Single<List<SubCategory>> getSubCategories(String categoryId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SUB_CATEGORIES)
                .addPathParameter("id", categoryId)
                .build()
                .getObjectListSingle(SubCategory.class);
    }



}