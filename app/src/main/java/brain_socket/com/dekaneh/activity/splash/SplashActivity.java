package brain_socket.com.dekaneh.activity.splash;

import android.os.Bundle;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.registration.RegistrationActivity;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.utils.NetworkUtils;

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
        if (NetworkUtils.isNetworkConnected(this)) {
            presenter.clearCache();
        }

        RegistrationActivity.start(this);
        finish();

    }
}
