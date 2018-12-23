package com.socket.dekaneh.activity.splash;

import android.os.Bundle;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.registration.RegistrationActivity;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.utils.NetworkUtils;

public class SplashActivity extends BaseActivity implements SplashActivityVP.View {

    @Inject
    SplashActivityVP.Presenter<SplashActivityVP.View> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getActivityComponent() != null) {
            getActivityComponent().inject(this);
        }

        presenter.onAttach(this);
//        if (NetworkUtils.isNetworkConnected(this)) {
//            presenter.clearCache();
//        }

        RegistrationActivity.start(this);
        finish();

    }
}
