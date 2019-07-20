package com.socket.dekaneh.base;

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.utils.GsonUtils;
import com.socket.dekaneh.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;
    private final CacheStore cacheStore;
    private T view;


    @Inject
    public BasePresenterImpl(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {

        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.cacheStore = cacheStore;
    }

    @Override
    public void onAttach(T mvpView) {
        this.view = mvpView;
        if (!isNetworkConnected()) {
            compositeDisposable.dispose();
            getView().hideLoading();
        }
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        this.view = null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public CacheStore getCacheStore() {
        return cacheStore;
    }

    public T getView() {
        return view;
    }

    @Override
    public void handleApiError(ANError error) {


        if (error == null) {
            getView().onError(R.string.api_default_error);
        } else if (error.getErrorCode() == AppApiHelper.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getView().onError(R.string.connection_error);
        } else if (error.getErrorBody() == null) {
            getView().onError(R.string.api_default_error);
        } else if (error.getErrorCode() == AppApiHelper.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getView().onError(R.string.api_retry_error);
        } else if (error.getErrorCode() == AppApiHelper.TOTAL_PRICE_IS_LOW_ERROR) {
            getView().onError(R.string.low_total_price_error);
        } else if (error.getErrorCode() == AppApiHelper.ALREADY_IN_FAVORITE_ERROR) {
            getView().onError(R.string.already_favorite);
        } else if (error.getErrorCode() == AppApiHelper.FAVORITE_NOT_SET_ERROR) {
            getView().onError(R.string.no_in_favorite);
        } else if (error.getErrorCode() == AppApiHelper.COUPON_NOT_FOUND_ERROR) {
            getView().onError(R.string.coupon_not_found_error);
        } else if (error.getErrorCode() == AppApiHelper.COUPON_IN_USE) {
            getView().onError(R.string.coupon_in_use_error);
        } else if (error.getErrorCode() == AppApiHelper.WRONG_CREDENTIALS) {
            getView().onError(R.string.err_wrong_credentials);
        } else if (error.getErrorCode() == AppApiHelper.PRODUCT_NOT_AVAILABLE_ERROR) {
            try {
                // the error body shall contain the product that is causing this issue
                JsonElement errBody = new JsonParser().parse(error.getErrorBody()).getAsJsonObject().get("error");
                JsonArray unavailableProductsJsonArray = errBody.getAsJsonObject().getAsJsonArray("data");
                String productString = unavailableProductsJsonArray.get(0).getAsJsonObject().toString();
                Product product = GsonUtils.convertJsonStringToProductObject(productString);

                getView().onError(getView().getContext().getString(R.string.product_not_available_error, product.getNameAr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (error.getErrorCode() == AppApiHelper.PRODUCT_AVAILABLE_STOCK_IS_LOW_ERROR) {
            try {
                // the error body shall contain the product that is causing this issue
                JsonElement errBody = new JsonParser().parse(error.getErrorBody()).getAsJsonObject().get("error");
                JsonArray unavailableProductsJsonArray = errBody.getAsJsonObject().getAsJsonArray("data");
                String productString = unavailableProductsJsonArray.get(0).getAsJsonObject().toString();
                Product product = GsonUtils.convertJsonStringToProductObject(productString);

                getView().onError(getView().getContext().getString(R.string.product_not_available_error, product.getNameAr()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.e("ERRRRRRRRRRR", "handleApiError: " + error.getErrorBody());
        }


    }

    @Override
    public void updateCartItemsCountText() {
        getView().updateMainActivityCartItemsCount(String.valueOf(getCacheStore().getCartItems().size()));
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getView().getContext());
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
