package brain_socket.com.dekaneh.activity;


import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivityPresenter<T extends MainActivityVP.View> extends BasePresenterImpl<T> implements MainActivityVP.Presenter<T> {

    public MainActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        getView().showMainFragment();
    }
}
