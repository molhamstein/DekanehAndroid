package com.socket.dekaneh.activity.rating;

import com.androidnetworking.error.ANError;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import com.socket.dekaneh.Rating;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RatingActivityPesenter<T extends RatingActivityVP.View> extends BasePresenterImpl<T> implements RatingActivityVP.Presenter<T> {

    private Rating.Rate rate;

    @Inject
    public RatingActivityPesenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
        rate = Rating.Rate.happy;
    }

    @Override
    public void submitRate() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.postRating(getCacheStore().getSession().getAccessToken(),
                        rate,
                        getCacheStore().getSession().getUserId(),
                        getView().getIntent().getExtras().getString("orderId"))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<JsonObject>() {
                            @Override
                            public void accept(JsonObject jsonObject) throws Exception {
                                getView().hideLoading();
                                getView().finish();
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
    public void setRate(Rating.Rate rate) {
        this.rate = rate;
    }
}
