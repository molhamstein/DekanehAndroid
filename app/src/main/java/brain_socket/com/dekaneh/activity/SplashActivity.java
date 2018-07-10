package brain_socket.com.dekaneh.activity;

import android.os.Bundle;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LoginActivity.start(this);
        finish();

    }
}
