package com.socket.dekaneh.activity.manufacturer;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.utils.GsonUtils;

import java.util.List;

import javax.inject.Inject;

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
        if (manufacturer.getNameAr() != null && manufacturer.getNameAr().equals(""))
            getView().setTitle(manufacturer.getNameAr());
        else fetchManufacturer();
    }

    @Override
    public void fetchProducts() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getProductsByManufacturer(manufacturer.getId(), getCacheStore().getSession().getAccessToken())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Product>>() {
                            @Override
                            public void accept(List<Product> products) throws Exception {
                                getView().hideLoading();
                                getView().addAllProducts(products);

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                handleApiError((ANError) throwable);


                            }
                        })
        );
    }

    @Override
    public void fetchManufacturer() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.fetchManufacturer(manufacturer.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Manufacturer>() {
                            @Override
                            public void accept(Manufacturer manufacturer) throws Exception {
                                getView().hideLoading();
                                getView().setTitle(manufacturer.getNameAr());

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                handleApiError((ANError) throwable);
                            }
                        })
        );
    }


}
