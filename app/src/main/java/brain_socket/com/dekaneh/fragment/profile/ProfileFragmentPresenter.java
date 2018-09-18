package brain_socket.com.dekaneh.fragment.profile;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.Order;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ProfileFragmentPresenter<T extends ProfileFragmentVP.View> extends BasePresenterImpl<T> implements ProfileFragmentVP.Presenter<T> {

    public static final String TAG = ProfileFragmentPresenter.class.getSimpleName();

    @Inject
    public ProfileFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void fetchOrders() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getOrders(getCacheStore().getSession().getUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> orders) throws Exception {

                        Log.d(TAG, "accept: " + orders.get(0).getStatus());
                        getView().hideLoading();
                        getView().addOrders(orders);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideLoading();
                        Log.e(TAG, "accept: ", throwable);
                    }
                })
        );
    }
}
