package brain_socket.com.dekaneh.activity.settings;

import android.util.Log;

import com.google.gson.JsonObject;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.utils.NetworkUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SettingsActivityPresenter<T extends SettingsActivityVP.View> extends BasePresenterImpl<T> implements SettingsActivityVP.Presenter<T> {

    public static final String TAG = SettingsActivityPresenter.class.getSimpleName();

    @Inject
    public SettingsActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }
//
//    @Override
//    public void logout() {
//
//        getView().showLoading();
//        getCompositeDisposable().add(
//                AppApiHelper.logout(getCacheStore().getSession().getAccessToken())
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String string) throws Exception {
//                        getView().hideLoading();
//                        getCacheStore().getSession().logout();
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e(TAG, "accept: ", throwable);
//                        getView().hideLoading();
//                    }
//                })
//        );
//
//    }

    @Override
    public void offlineLogout() {
        getCacheStore().getSession().logout();
    }
}
