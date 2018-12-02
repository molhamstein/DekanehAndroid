package com.socket.dekaneh.activity.manufacturer;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.network.model.ManufacturerProduct;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.utils.GsonUtils;
import com.socket.dekaneh.utils.NetworkUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ManufacturerActivityPresenter<T extends ManufacturerActivityVP.View> extends BasePresenterImpl<T> implements ManufacturerActivityVP.Presenter<T> {

    private Manufacturer manufacturer;


    @Inject
    public ManufacturerActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        this.manufacturer = GsonUtils.convertJsonStringToManufacturerObject(getView().getIntent().getExtras().getString(Manufacturer.TAG));
        getView().setTitle(manufacturer.getNameAr());
    }

    @Override
    public void fetchProducts() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getProductsByManufacturer(manufacturer.getId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<ManufacturerProduct>>() {
                    @Override
                    public void accept(List<ManufacturerProduct> offers) throws Exception {
                        getView().hideLoading();
                        getView().addAllProducts(offers);
                        Log.d("ADASDASD", "accept: " + offers.toString());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("ASDQWEASDZXCSDFDGF", "accept: " + NetworkUtils.getError(throwable));
                        getView().hideLoading();
                        getView().showMessage(throwable.toString());

                    }
                })
        );
    }
}
