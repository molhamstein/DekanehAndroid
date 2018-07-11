package brain_socket.com.dekaneh.activity;

import android.os.Bundle;

import brain_socket.com.dekaneh.activity.registration.RegistrationActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RegistrationActivity.start(this);
        finish();

    }
}
