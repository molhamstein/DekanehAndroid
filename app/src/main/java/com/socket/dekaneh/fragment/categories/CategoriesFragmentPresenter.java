package com.socket.dekaneh.fragment.categories;

import java.util.List;
import javax.inject.Inject;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Category;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CategoriesFragmentPresenter<T extends CategoriesFragmentVP.View> extends BasePresenterImpl<T> implements CategoriesFragmentVP.Presenter<T> {

    @Inject
    public CategoriesFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        if (getCacheStore().getCategories() != null)
            getView().addCategories(getCacheStore().getCategories());
        else fetchCategories();

    }

    @Override
    public void fetchCategories() {
        getCompositeDisposable().add(
                AppApiHelper.getCategories()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Category>>() {
                            @Override
                            public void accept(List<Category> categories) throws Exception {

                                getView().addCategories(categories);
                                getCacheStore().cacheCategories(categories);

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );

    }
}
