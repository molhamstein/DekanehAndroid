package brain_socket.com.dekaneh.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.BaseActivity;
import brain_socket.com.dekaneh.fragment.BaseFragment;
import brain_socket.com.dekaneh.fragment.LoginFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends BaseActivity implements RegistrationActivityVP.View {


    private static final String TAG = RegistrationActivity.class.getSimpleName();
    @BindView(R.id.registrationContainer)
    View container;

    RegistrationActivityPresenter presenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegistrationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        presenter = new RegistrationActivityPresenter(this);

        setFragment(LoginFragment.newInstance());
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        if (fragment.TAG().equals(LoginFragment.class.getSimpleName())){
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragment.attachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.registrationContainer, fragment)
                .addToBackStack(fragment.TAG())
                .commit();
    }

}