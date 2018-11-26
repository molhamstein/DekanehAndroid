package com.socket.dekaneh.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.main.MainActivity;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.fragment.registration.login.LoginFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends BaseActivity implements RegistrationActivityVP.View {


    private static final String TAG = RegistrationActivity.class.getSimpleName();
    @BindView(R.id.registrationContainer)
    View container;

    @Inject
    RegistrationActivityPresenter<RegistrationActivityVP.View> presenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegistrationActivity.class);
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        presenter.onAttach(this);

        setFragment(LoginFragment.newInstance());
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        if (fragment.TAG().equals(LoginFragment.class.getSimpleName())) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragment.attachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.registrationContainer, fragment)
                .addToBackStack(fragment.TAG())
                .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_to_right,
                        R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_to_right)
                .commit();
    }

    @Override
    public void startMainActivity() {
        MainActivity.start(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else
            super.onBackPressed();
    }
}