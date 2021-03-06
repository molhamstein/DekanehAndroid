package com.socket.dekaneh.fragment.main;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.SliderImage;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainFragmentPresenter<T extends MainFragmentVP.View> extends BasePresenterImpl<T> implements MainFragmentVP.Presenter<T> {

    public static final String TAG = MainFragmentPresenter.class.getSimpleName();

    @Inject
    public MainFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    private void updateFromCacheOrNetwork() {
        fetchFeaturedProducts();
        fetchFeaturedOffers();
        fetchCategories();
        fetchSliderImages();
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        updateFromCacheOrNetwork();
    }

    @Override
    public void fetchCategories() {
        if (getCacheStore().getHomeCategories() != null)
            getView().addCategoriesWithProducts(getCacheStore().getHomeCategories());
        if (isNetworkConnected()) {
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getHomeCategories(getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<HomeCategory>>() {
                                           @Override
                                           public void accept(List<HomeCategory> homeCategories) throws Exception {
                                               getView().addCategoriesWithProducts(homeCategories);
                                               getCacheStore().cacheHomeCategories(homeCategories);
                                               getView().hideLoading();

                                           }
                                       }, new Consumer<Throwable>() {
                                           @Override
                                           public void accept(Throwable throwable) throws Exception {
                                               handleApiError((ANError) throwable);
                                               getView().hideLoading();
                                               Log.e(TAG, "accept: fetchCategories");
                                           }
                                       }
                            )
            );
        }
    }

    @Override
    public void fetchFeaturedOffers() {
        if (getCacheStore().getFeaturedOffers() != null)
            getView().addFeaturedOffers(getCacheStore().getFeaturedOffers());
        if (isNetworkConnected()) {
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getFeaturedOffers(getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<Offer>>() {
                                @Override
                                public void accept(List<Offer> offers) throws Exception {

                                    getView().hideLoading();
                                    getView().addFeaturedOffers(offers);
                                    getCacheStore().cacheFeaturedOffers(offers);


                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e(TAG, "accept: fetchFeaturedOffers", throwable);
                                    handleApiError((ANError) throwable);
                                    getView().hideLoading();
                                }
                            })
            );
        }
    }

    @Override
    public void fetchFeaturedProducts() {
        if (isNetworkConnected()) {

            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getFeaturedProducts(getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<Product>>() {
                                @Override
                                public void accept(List<Product> products) throws Exception {

                                    getView().hideLoading();
                                    getView().addFeaturedProducts(products);


                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e(TAG, "accept: fetchFeaturedProducts");
                                    handleApiError((ANError) throwable);
                                    getView().hideLoading();
                                }
                            })
            );
        }
    }

    @Override
    public void fetchSliderImages() {
        if (isNetworkConnected()) {

            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getSliderImages(getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<SliderImage>>() {
                                @Override
                                public void accept(List<SliderImage> sliderImages) throws Exception {
                                    getView().addSliderImages(sliderImages);
                                    getView().hideLoading();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e(TAG, "accept: fetchSliderImages");
                                    handleApiError((ANError) throwable);
                                    getView().hideLoading();
                                }
                            })
            );
        }
    }

    @Override
    public void onFragmentResume() {
        updateFromCacheOrNetwork();
    }
}
