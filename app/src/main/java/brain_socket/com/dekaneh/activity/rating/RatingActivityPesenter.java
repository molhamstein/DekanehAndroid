package brain_socket.com.dekaneh.activity.rating;

import com.google.gson.JsonObject;

import javax.inject.Inject;

import brain_socket.com.dekaneh.Rating;
import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RatingActivityPesenter<T extends RatingActivityVP.View> extends BasePresenterImpl<T> implements RatingActivityVP.Presenter<T> {

    private Rating.Rate rate;

    @Inject
    public RatingActivityPesenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void submitRate() {
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

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );
    }

    @Override
    public void setRate(Rating.Rate rate) {
        this.rate = rate;
    }
}
