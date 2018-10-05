package brain_socket.com.dekaneh.fragment.registration.new_account;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.github.florent37.viewanimator.AnimationListener;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.SignUpRequest;
import brain_socket.com.dekaneh.network.model.User;
import brain_socket.com.dekaneh.utils.NetworkUtils;
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

        if (!getView().areFieldsEmpty()) {
            getView().showMessage("All Fields are required");
        } else {

            getView().showLoading();

            getCompositeDisposable().add(
                    AppApiHelper.signUp(request)
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<User>() {
                                @Override
                                public void accept(User user) throws Exception {
                                    getView().hideLoading();
                                    getView().showMessage("Thank you " + user.getOwnerName() + "!. Your request had been submitted");
                                    getView().outAnimation(new AnimationListener.Stop() {
                                        @Override
                                        public void onStop() {
                                            getView().onSuccessfulSignUp();
                                        }
                                    });
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    if (throwable instanceof ANError) {
                                        Log.d("", "accept: " + NetworkUtils.getError(throwable));
                                        getView().showMessage(NetworkUtils.getError(throwable));
                                    }
                                }
                            })
            );
        }
    }
}
