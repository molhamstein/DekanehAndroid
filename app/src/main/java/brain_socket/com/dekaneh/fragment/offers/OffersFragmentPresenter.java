package brain_socket.com.dekaneh.fragment.offers;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.Offer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class OffersFragmentPresenter<T extends OffersFragmentVP.View> extends BasePresenterImpl<T> implements OffersFragmentVP.Presenter<T> {


    @Inject
    public OffersFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void fetchOffers() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getOffers()
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
                            }
                        })
        );
    }
}
