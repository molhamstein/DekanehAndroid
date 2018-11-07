package brain_socket.com.dekaneh.activity.splash;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.CacheStore;
import io.reactivex.disposables.CompositeDisposable;

public class SplashActivityPresenter<T extends SplashActivityVP.View> extends BasePresenterImpl<T> implements SplashActivityVP.Presenter<T> {

    @Inject
    public SplashActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void clearCache() {
        getCacheStore().clearCacheWithoutCart();
    }
}
