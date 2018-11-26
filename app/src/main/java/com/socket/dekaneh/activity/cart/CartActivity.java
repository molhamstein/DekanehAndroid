package com.socket.dekaneh.activity.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.CartOrdersAdapter;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.network.model.CartItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity implements CartActivityVP.View {

    @Inject
    CartActivityVP.Presenter<CartActivityVP.View> presenter;
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
                if (isCartClear) {
                    setOrderViewClear(true);
                }
                setOkResult();
            }
        });
        total.setText(String.valueOf(presenter.getPrice()));

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

    @OnClick(R.id.sendBtn)
    public void onSendOrderBtnClicked() {
        presenter.sendOrder();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}