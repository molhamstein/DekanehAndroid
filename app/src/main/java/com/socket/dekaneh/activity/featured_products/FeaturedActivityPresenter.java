package com.socket.dekaneh.activity.featured_products;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Product;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class FeaturedActivityPresenter<T extends FeaturedActivityVP.View> extends BasePresenterImpl<T> implements FeaturedActivityVP.Presenter<T> {

    @Inject
    public FeaturedActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void fetchProducts() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getFeaturedProducts(getCacheStore().getSession().getAccessToken())
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
}
