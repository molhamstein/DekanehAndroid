package com.socket.dekaneh.fragment.profile;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.settings.SettingsActivity;
import com.socket.dekaneh.adapter.OrdersAdapter;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.utils.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements ProfileFragmentVP.View {

    @Inject
    ProfileFragmentVP.Presenter<ProfileFragmentVP.View> presenter;

    @BindView(R.id.map)
    ImageView map;
    @BindView(R.id.profileOrdersRV)
    RecyclerView profileOrdersRV;
    @BindView(R.id.bottomSheet)
    View bottomSheet;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.storeName)
    TextView storeName;
    @BindView(R.id.ownerName)
    TextView ownerName;
    @BindView(R.id.phoneNumber)
    TextView phoneNumber;
    @BindView(R.id.businessNameForm)
    EditText businessNameForm;
    @BindView(R.id.phoneNumberForm)
    EditText phoneNumberForm;
    @BindView(R.id.ownerNameForm)
    EditText ownerNameForm;


    @Inject
    OrdersAdapter ordersAdapter;

    BottomSheetBehavior behavior;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void init(View rootView) {

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        presenter.onAttach(this);
        presenter.fetchOrders();

        profileOrdersRV.setLayoutManager(new LinearLayoutManager(getContext()));
        profileOrdersRV.setAdapter(ordersAdapter);
        profileOrdersRV.setNestedScrollingEnabled(false);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        tabLayout.addTab(tabLayout.newTab().setCustomView(ViewUtils.getTabTextView(getContext(), getString(R.string.order))));
        if (!presenter.hideHistory())
            tabLayout.addTab(tabLayout.newTab().setCustomView(ViewUtils.getTabTextView(getContext(), getString(R.string.past_orders))));
        tabLayout.addTab(tabLayout.newTab().setCustomView(ViewUtils.getTabTextView(getContext(), getString(R.string.rewards))));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        presenter.fetchOrders();
                        break;
                    case 1:
                        presenter.fetchPastOrders();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public String TAG() {
        return this.getClass().getSimpleName();
    }

    @OnClick(R.id.profileSettingsIcon)
    public void onSettingsActionClick() {
        SettingsActivity.start(getContext());
    }

    @OnClick(R.id.profileEditIcon)
    public void onEditActionClick() {
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void addOrders(List<Order> orders) {
        ordersAdapter.addAllOrderes(orders);
    }

    @Override
    public void updateView(String storeName, String ownerName, String phoneNumber) {
        this.storeName.setText(storeName);
        this.ownerName.setText(ownerName);
        this.phoneNumber.setText(phoneNumber);
        this.businessNameForm.setText(storeName);
        this.phoneNumberForm.setText(phoneNumber);
        this.ownerNameForm.setText(ownerName);
    }

    @Override
    public void updateMap(String url) {
        Picasso.get().load(url).into(map);
    }

    @OnClick(R.id.updateUserBtn)
    public void onClickedUpdateUserBtn() {
        String storeName = this.businessNameForm.getText().toString();
        String ownerName = this.ownerNameForm.getText().toString();
        String phoneNumber = this.phoneNumberForm.getText().toString();
        presenter.patchUser(storeName, ownerName, phoneNumber);
    }


    @Override
    public void onResume() {
        if (presenter != null)
            presenter.fetchOrders();
        super.onResume();
    }
}
