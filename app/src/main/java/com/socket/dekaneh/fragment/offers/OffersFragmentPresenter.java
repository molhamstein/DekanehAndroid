package com.socket.dekaneh.fragment.offers;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Offer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class OffersFragmentPresenter<T extends OffersFragmentVP.View> extends BasePresenterImpl<T> implements OffersFragmentVP.Presenter<T> {


    @Inject
    public OffersFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void fetchOffers() {
        if (isNetworkConnected()) {
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.getOffers(getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<List<Offer>>() {
                                @Override
                                public void accept(List<Offer> offers) throws Exception {
                                    getView().addOffers(offers);
                                    getView().hideLoading();
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
}
