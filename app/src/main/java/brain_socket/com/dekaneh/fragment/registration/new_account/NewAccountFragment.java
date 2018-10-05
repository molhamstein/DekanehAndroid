package brain_socket.com.dekaneh.fragment.registration.new_account;


import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.custom.DekanehInterpolator;
import brain_socket.com.dekaneh.fragment.registration.SubmitAccountFragment;
import brain_socket.com.dekaneh.fragment.registration.login.LoginFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class NewAccountFragment extends BaseFragment implements NewAccountFragmentVP.View {
    @Inject
    NewAccountFragmentVP.Presenter<NewAccountFragmentVP.View> presenter;

    @BindView(R.id.newAccountPhoneCard)
    View phoneCard;
    @BindView(R.id.newAccountStoreNameCard)
    View storeNameCard;
    @BindView(R.id.newAccountOwnerNameCard)
    View ownerNameLayout;
    @BindView(R.id.newAccountAddress)
    View storeAddressCard;
    @BindView(R.id.newAccountPasswordCard)
    View passwordCard;
    @BindView(R.id.newAccountSubmitBtn)
    View submitBtn;
    @BindView(R.id.newAccountLoginLayout)
    View loginLayout;
    @BindView(R.id.newAccountPolicyLayout)
    View policyLayout;
    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.phoneNumber)
    EditText phoneNumber;
    @BindView(R.id.storeName)
    EditText storeName;
    @BindView(R.id.ownerName)
    EditText ownerName;
    @BindView(R.id.storeLocation)
    EditText storeLocation;
    @BindView(R.id.password)
    EditText password;

    public NewAccountFragment() {
    }

    public static NewAccountFragment newInstance() {
        NewAccountFragment fragment = new NewAccountFragment();
        return fragment;
    }


    @Override
    public void init(View rootView) {

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        presenter.onAttach(this);

        ViewAnimator.animate(phoneCard).translationX(-600, 0).alpha(0, 1).duration(800)
                .andAnimate(storeNameCard).translationX(-700, 0).alpha(0, 1).duration(800)
                .andAnimate(ownerNameLayout).translationX(-750, 0).alpha(0, 1).duration(800)
                .andAnimate(storeAddressCard).translationX(-800, 0).alpha(0, 1).duration(800)
                .andAnimate(passwordCard).translationX(-900, 0).alpha(0, 1).duration(800)
                .andAnimate(policyLayout).slideLeft().fadeIn().duration(800)
                .andAnimate(submitBtn).translationX(-1000, 0).alpha(0, 1).duration(800)
                .andAnimate(loginLayout).slideLeft().fadeIn().duration(800)
                .interpolator(new DekanehInterpolator(1f))
                .start();

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_new_account;
    }

    @Override
    public String TAG() {
        return NewAccountFragment.class.getSimpleName();
    }

    @OnClick(R.id.signInText)
    public void onSignInTextClicked() {
        outAnimation(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                navigationPresenter.replaceFragment(LoginFragment.newInstance());
            }
        });
    }

    @OnClick(R.id.newAccountSubmitBtn)
    public void onSubmitBtnClicked() {
        if (checkBox.isChecked()) {
            presenter.signUp(phoneNumber.getText().toString(),
                    storeName.getText().toString(),
                    ownerName.getText().toString(),
                    "",
                    password.getText().toString());
        }else{
            showMessage(getString(R.string.terms_conditions_msg));
        }
            }

    @Override
    public void outAnimation(AnimationListener.Stop onStop) {
        ViewAnimator.animate(phoneCard).translationX(0, 600).alpha(1, 0).duration(600)
                .andAnimate(storeNameCard).translationX(0, 700).alpha(1, 0).duration(600)
                .andAnimate(ownerNameLayout).translationX(0, 750).alpha(1, 0).duration(600)
                .andAnimate(storeAddressCard).translationX(0, 800).alpha(1, 0).duration(600)
                .andAnimate(passwordCard).translationX(0, 900).alpha(1, 0).duration(600)
                .andAnimate(policyLayout).translationX(0, 900).alpha(1, 0).duration(600)
                .andAnimate(submitBtn).translationX(0, 1000).alpha(1, 0).duration(600)
                .andAnimate(loginLayout).translationX(0, 1000).alpha(1, 0).duration(600)
                .interpolator(new DekanehInterpolator(1f))
                .onStop(onStop)
                .start();
    }

    @Override
    public boolean areFieldsEmpty() {
        return !password.getText().toString().isEmpty()
                && !storeName.getText().toString().isEmpty()
                && !ownerName.getText().toString().isEmpty()
                && !phoneNumber.getText().toString().isEmpty()
                ;
    }

    @Override
    public void onSuccessfulSignUp() {
        navigationPresenter.replaceFragment(SubmitAccountFragment.newInstance());
    }

}

