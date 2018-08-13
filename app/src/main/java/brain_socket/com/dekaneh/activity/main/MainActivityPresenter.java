package brain_socket.com.dekaneh.activity.main;


import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivityPresenter<T extends MainActivityVP.View> extends BasePresenterImpl<T> implements MainActivityVP.Presenter<T> {

    @Inject
    public MainActivityPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        getView().showMainFragment();
    }

    @Override
    public void onBottomNavMainItemClick() {
        getView().showMainFragment();
        getView().showToolbarTitle(false);
        getView().showToolbarLogo(true);
    }

    @Override
    public void onBottomOffersItemClick() {
        getView().showOffersFragment();
        getView().showToolbarLogo(false);
        getView().showToolbarTitle(true);
        getView().setToolbarTitle(R.string.offers);
    }

    @Override
    public void onBottomCategoriesItemClick() {
        getView().showCategoriesFragment();
        getView().showToolbarLogo(false);
        getView().showToolbarTitle(true);
        getView().setToolbarTitle(R.string.categories);
    }
}
