package brain_socket.com.dekaneh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import brain_socket.com.dekaneh.R;
import butterknife.BindView;

public class RegistrationActivity extends BaseActivity {

    @BindView(R.id.registrationContainer)
    View container;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegistrationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}
