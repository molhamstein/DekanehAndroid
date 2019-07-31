package com.socket.dekaneh.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    Intent getIntent();

    void updateMainActivityCartItemsCount(String count);

    Context getContext();

    void handleVersionResponse(Integer errorCode);

}
