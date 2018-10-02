package brain_socket.com.dekaneh.activity.category_details;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.Manufacturer;
import brain_socket.com.dekaneh.network.model.SubCategory;
import brain_socket.com.dekaneh.utils.GsonUtils;
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
