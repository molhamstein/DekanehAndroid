package com.socket.dekaneh.activity.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.CartOrdersAdapter;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.network.model.CartItem;

import brain_socket.com.dekaneh.activity.cart.CartActivityVP;

import com.socket.dekaneh.adapter.CouponsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.socket.dekaneh.network.model.Coupon;

public class CartActivity extends BaseActivity implements CartActivityVP.View {

    @Inject
    CartActivityVP.Presenter<CartActivityVP.View> presenter;
    @Inject
    CouponsAdapter couponsAdapter;


    @BindView(R.id.cartOrderRV)
    RecyclerView ordersRV;
    @BindView(R.id.cartToolbar)
    Toolbar toolbar;
    @Inject
    CartOrdersAdapter cartAdapter;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.ordersView)
    View ordersView;
    @BindView(R.id.emptyCartImg)
    View emptyCartImg;
    @BindView(R.id.emptyCartText)
    View emptyCartText;
    @BindView(R.id.sendBtn)
    Button orderButton;
    @BindView(R.id.bottomSheet)
    View bottomSheet;
    @BindView(R.id.couponsRV)
    RecyclerView couponsRV;
    @BindView(R.id.couponDiscount)
    TextView couponDiscount;
    @BindView(R.id.couponDiscountLayout)
    View couponDiscountLayout;
    @BindView(R.id.subTotal)
    TextView subTotal;
    @BindView(R.id.percent)
    View percent;
    @BindView(R.id.fixed)
    View fixed;
    @BindView(R.id.addCouponBtn)
    Button addCouponBtn;
    @BindView(R.id.couponCodeText)
    EditText couponCodeText;
    @BindView(R.id.note)
    EditText noteEditText;
    @BindView(R.id.notesBottomSheet)
    View notesBottomSheet;
    @BindView(R.id.submitNoteBtn)
    View submitNoteBtn;
    @BindView(R.id.addNoteBtn)
    View addNoteBtn;


    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetBehavior noteBottomSheetBehavior;

    public static void start(Context context) {
        Intent starter = new Intent(context, CartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        presenter.onAttach(this);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        noteBottomSheetBehavior = BottomSheetBehavior.from(notesBottomSheet);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ordersRV.setLayoutManager(new LinearLayoutManager(this));
        ordersRV.setAdapter(cartAdapter);
        presenter.fetchItems();
        cartAdapter.setOnQuantityChangedListener(new CartOrdersAdapter.OnQuantityChangedListener() {
            @Override
            public void onChanged(boolean isCartClear) {
                total.setText(String.valueOf(presenter.getPrice()));
                subTotal.setText(String.valueOf(presenter.getSubtotalPrice()));
                if (isCartClear) {
                    setOrderViewClear(true);
                }
                setOkResult();
            }
        });
        total.setText(String.valueOf(presenter.getPrice()));
        subTotal.setText(String.valueOf(presenter.getSubtotalPrice()));


        couponsRV.setLayoutManager(new LinearLayoutManager(this));
        couponsRV.setAdapter(couponsAdapter);

        presenter.getCoupons();

        couponsAdapter.setOnCouponClick(new CouponsAdapter.OnCouponClick() {
            @Override
            public void onClick(Coupon coupon, int position) {
                presenter.setCoupon(coupon);

            }
        });
        ordersRV.setNestedScrollingEnabled(false);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    addCouponBtn.setText(R.string.add_coupon);
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    addCouponBtn.setText(R.string.coupons);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addAllItems(List<CartItem> items) {
        cartAdapter.addAllItems(items);
    }

    @Override
    public void setOrderViewClear(boolean clear) {
        if (clear) {
            noteEditText.setText("");
            ordersView.setVisibility(View.GONE);
            emptyCartImg.setVisibility(View.VISIBLE);
            emptyCartText.setVisibility(View.VISIBLE);
        } else {
            ordersView.setVisibility(View.VISIBLE);
            emptyCartImg.setVisibility(View.GONE);
            emptyCartText.setVisibility(View.GONE);
        }
    }

    @Override
    public void setOkResult() {
        setResult(RESULT_OK);
    }

    @Override
    public void disableOrderBtn() {
        orderButton.setEnabled(false);
        orderButton.setClickable(false);
        orderButton.setBackgroundResource(R.drawable.disabled_button_round_10);
    }

    @Override
    public void addAllCoupons(List<Coupon> coupons) {
        couponsAdapter.addAllCoupons(coupons);
    }

    @Override
    public void updatePriceAfterCoupon(int couponValue, boolean isFixed) {
        couponDiscountLayout.setVisibility(View.VISIBLE);
        couponDiscount.setText(String.valueOf(couponValue));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        total.setText(String.valueOf(presenter.getPrice()));
        if (isFixed) {
            fixed.setVisibility(View.VISIBLE);
            percent.setVisibility(View.GONE);
        } else {
            percent.setVisibility(View.VISIBLE);
            fixed.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.sendBtn)
    public void onSendOrderBtnClicked() {
        presenter.sendOrder();
    }

    @OnClick(R.id.addCouponBtn)
    public void onAddCouponBtn() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @OnClick(R.id.addCouponCodeBtn)
    public void onAddCouponCodeBtn() {
        presenter.getCoupon(couponCodeText.getText().toString());
        couponCodeText.setText("");
    }

    @OnClick(R.id.submitNoteBtn)
    public void onSubmitNoteBtnClicked() {
        presenter.setNote(noteEditText.getText().toString());
        noteBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @OnClick(R.id.addNoteBtn)
    public void onAddNoteBtn() {
        noteBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}