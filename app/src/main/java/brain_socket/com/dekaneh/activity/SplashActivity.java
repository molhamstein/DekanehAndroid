package brain_socket.com.dekaneh.activity;

import android.os.Bundle;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.registration.RegistrationActivity;
import brain_socket.com.dekaneh.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RegistrationActivity.start(this);
        finish();

    }
}
