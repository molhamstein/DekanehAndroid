package brain_socket.com.dekaneh.fragment.registration.new_account;

import android.util.Log;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.SignUpRequest;
import brain_socket.com.dekaneh.network.model.User;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NewAccountFragmentPresenter<T extends NewAccountFragmentVP.View> extends BasePresenterImpl<T> implements NewAccountFragmentVP.Presenter<T> {

    @Inject
    public NewAccountFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void signUp(String phoneNumber, String storeName, String ownerName, String location, String password) {

        SignUpRequest request = new SignUpRequest(phoneNumber, storeName, ownerName, password);

        getCompositeDisposable().add(
                AppApiHelper.signUp(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        Log.d("SSS", "accept: " + user.getOwnerName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
        );
    }
}
