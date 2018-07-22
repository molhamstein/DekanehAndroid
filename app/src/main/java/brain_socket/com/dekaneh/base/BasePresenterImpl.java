package brain_socket.com.dekaneh.base;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;
    private T view;


    @Inject
    public BasePresenterImpl(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {

        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(T mvpView) {
        this.view = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        this.view = null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public T getView() {
        return view;
    }

    @Override
    public void handleApiError(ANError error) {

    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
