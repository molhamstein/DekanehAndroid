package brain_socket.com.dekaneh.fragment;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.OrdersAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import brain_socket.com.dekaneh.utils.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.profileOrdersRV)
    RecyclerView profileOrdersRV;
    @BindView(R.id.bottomSheet)
    View bottomSheet;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    BottomSheetBehavior behavior;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void init(View rootView) {

        profileOrdersRV.setLayoutManager(new LinearLayoutManager(getContext()));
        profileOrdersRV.setAdapter(new OrdersAdapter());
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        tabLayout.addTab(tabLayout.newTab().setCustomView(ViewUtils.getTabTextView(getContext(), "Orders")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(ViewUtils.getTabTextView(getContext(), "Post Orders")));
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
    public void onSettingsActionClick(){
    }

    @OnClick(R.id.profileEditIcon)
    public void onEditActionClick(){
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
