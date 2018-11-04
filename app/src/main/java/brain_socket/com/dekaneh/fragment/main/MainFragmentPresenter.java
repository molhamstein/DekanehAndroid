package brain_socket.com.dekaneh.fragment.main;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.base.BasePresenterImpl;
import brain_socket.com.dekaneh.network.AppApiHelper;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.SliderImage;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainFragmentPresenter<T extends MainFragmentVP.View> extends BasePresenterImpl<T> implements MainFragmentVP.Presenter<T> {

    public static final String TAG = MainFragmentPresenter.class.getSimpleName();

    @Inject
    public MainFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        if (getCacheStore().getHomeCategories() != null && getCacheStore().getFeaturedOffers() != null) {
            getView().addCategoriesWithProducts(getCacheStore().getHomeCategories());
            getView().addFeaturedOffers(getCacheStore().getFeaturedOffers());
        }
        else {
            fetchFeaturedOffers();
            fetchCategories();
        }

    }

    @Override
    public void fetchCategories() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getHomeCategories()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<HomeCategory>>() {
                                       @Override
                                       public void accept(List<HomeCategory> homeCategories) throws Exception {
                                           getView().addCategoriesWithProducts(homeCategories);
                                           getCacheStore().cacheHomeCategories(homeCategories);
                                           getView().hideLoading();
                                           Log.d(TAG, "accept: " + homeCategories.get(0).getProducts().toString());

                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           getView().showMessage(throwable.getMessage());
                                           getView().hideLoading();
                                       }
                                   }
                        )
        );
    }

    @Override
    public void fetchFeaturedOffers() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getFeaturedOffers()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Offer>>() {
                            @Override
                            public void accept(List<Offer> offers) throws Exception {

                                getView().hideLoading();
                                getView().addFeaturedOffers(offers);
                                getCacheStore().cacheFeaturedOffers(offers);


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();

                            }
                        })
        );
    }

    @Override
    public void fetchSliderImages() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getSliderImages()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<SliderImage>>() {
                    @Override
                    public void accept(List<SliderImage> sliderImages) throws Exception {
                        getView().addSliderImages(sliderImages);
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
