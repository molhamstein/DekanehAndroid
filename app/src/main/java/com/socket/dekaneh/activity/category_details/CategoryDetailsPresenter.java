package com.socket.dekaneh.activity.category_details;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import com.androidnetworking.error.ANError;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.network.model.SubCategory;
import com.socket.dekaneh.utils.GsonUtils;
import com.socket.dekaneh.utils.NetworkUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CategoryDetailsPresenter<T extends CategoryDetailsVP.View> extends BasePresenterImpl<T> implements CategoryDetailsVP.Presenter<T> {
    public static final String TAG = CategoryDetailsPresenter.class.getSimpleName();
    private Category category;

    @Inject
    public CategoryDetailsPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        category = GsonUtils.convertJsonStringToCategoryObject(getView().getIntent().getExtras().getString(Category.TAG));
        getView().setTitle(category.getTitleAr());
        fetchManufacturers();
        fetchSubCategories();
    }

    @Override
    public void fetchSubCategories() {
        Log.d(TAG, "fetchSubCategories: " + category.getId());
        getCompositeDisposable().add(
                AppApiHelper.getSubCategories(category.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<SubCategory>>() {
                            @Override
                            public void accept(List<SubCategory> subCategories) throws Exception {
                                getView().addAllSubCategories(subCategories);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                handleApiError((ANError) throwable);
                            }
                        })
        );
    }

    @Override
    public void fetchManufacturers() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getCategoryManufacturers(category.getId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Manufacturer>>() {
                            @Override
                            public void accept(List<Manufacturer> manufacturers) throws Exception {
                                getView().addAllManufacturers(manufacturers);
                                getView().hideLoading();
                                Log.d(TAG, "accept: " + manufacturers.toString());
                                Log.d(TAG, "accept:  cat id =   " + category.getId());
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

    @Override
    public void fetchManufacturers(String subCategoryId) {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getCategoryManufacturers(category.getId(), subCategoryId)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Manufacturer>>() {
                            @Override
                            public void accept(List<Manufacturer> manufacturers) throws Exception {
                                getView().addAllManufacturers(manufacturers);
                                getView().hideLoading();
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
