package brain_socket.com.dekaneh.activity.registration;

import android.util.Log;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.CacheStore;
import io.reactivex.disposables.CompositeDisposable;

public class RegistrationActivityPresenter<T extends RegistrationActivityVP.View> extends BasePresenterImpl<T> implements RegistrationActivityVP.Presenter<T>, FragmentNavigationVP.Presenter{


    @Inject
    public RegistrationActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        if (getCacheStore().getSession().isLoggedOn()){
            Log.d("xcvxcvxcv", "onAttach: on" );
            getView().startMainActivity();
            getView().finish();
        }
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        getView().setFragment(fragment);
    }
}
