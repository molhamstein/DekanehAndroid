package brain_socket.com.dekaneh.activity.manufacturer;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.utils.GsonUtils;
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
                .subscribe(new Consumer<List<Offer>>() {
                    @Override
                    public void accept(List<Offer> offers) throws Exception {
                        getView().hideLoading();
                        getView().addAllProducts(offers);
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
