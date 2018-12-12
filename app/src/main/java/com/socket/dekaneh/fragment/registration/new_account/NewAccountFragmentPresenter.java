package com.socket.dekaneh.fragment.registration.new_account;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.github.florent37.viewanimator.AnimationListener;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.application.SchedulerProvider;
import com.socket.dekaneh.base.BasePresenterImpl;
import com.socket.dekaneh.network.AppApiHelper;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Area;
import com.socket.dekaneh.network.model.SignUpRequest;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.NetworkUtils;
import com.socket.dekaneh.utils.ValidationUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NewAccountFragmentPresenter<T extends NewAccountFragmentVP.View> extends BasePresenterImpl<T> implements NewAccountFragmentVP.Presenter<T> {

    private List<Area> areas;

    @Inject
    public NewAccountFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        fetchAreas();
    }

    @Override
    public void signUp(String phoneNumber, String storeName, String ownerName, String location, String password, int areaPos) {

        SignUpRequest request = new SignUpRequest(ValidationUtils.validatePhoneNumber(phoneNumber), storeName, ownerName, password, areas.get(areaPos).getId(), User.Type.wholesale);

        if(!ValidationUtils.isValidPhoneNumber(phoneNumber)) {
            getView().onError(R.string.provide_valid_phone_number_statement);
            return;
        }

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
                                    getView().hideLoading();
                                    handleApiError((ANError) throwable);

                                }
                            })
            );
        }
    }

    @Override
    public void fetchAreas() {
        getCompositeDisposable().add(
                AppApiHelper.getAreas(getCacheStore().getSession().getAccessToken())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Area>>() {
                            @Override
                            public void accept(List<Area> areas) throws Exception {
                                getView().setAllAreas(areas);
                                NewAccountFragmentPresenter.this.areas = areas;
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })

        );
    }


}
