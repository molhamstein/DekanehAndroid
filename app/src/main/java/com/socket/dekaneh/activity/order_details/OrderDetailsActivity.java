package com.socket.dekaneh.activity.order_details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.OrderDetailsItemsAdapter;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Coupon;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.utils.GsonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsVP.View {


    @Inject
    OrderDetailsVP.Presenter<OrderDetailsVP.View> presenter;
    @Inject
    OrderDetailsItemsAdapter adapter;
    @BindView(R.id.orderDetailsToolbar)
    Toolbar toolbar;
    @BindView(R.id.creationDate)
    TextView creationDate;
    @BindView(R.id.cancelOrderBtn)
    View cancelOrderBtn;
    @BindView(R.id.orderId)
    TextView orderId;
    @BindView(R.id.orderStatus)
    TextView orderStatus;
    @BindView(R.id.itemsRV)
    RecyclerView itemsRV;
    @BindView(R.id.total)
    TextView total;
    MenuItem edit;
    MenuItem submit;
    @BindView(R.id.couponLayout)
    View couponLayout;
    @BindView(R.id.orderCouponCode)
    TextView orderCouponCode;
    @BindView(R.id.orderPriceBeforeDiscount)
    TextView orderPriceBeforeDiscount;
    @BindView(R.id.orderCouponValue)
    TextView orderCouponValue;


    public static void start(Context context, Order order) {
        Intent starter = new Intent(context, OrderDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Order.TAG, GsonUtils.convertObjectToJson(order));
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getActivityComponent() != null) {
            getActivityComponent().inject(this);
        }
        presenter.onAttach(this);
        presenter.fetchItems();

        itemsRV.setLayoutManager(new LinearLayoutManager(this));
        itemsRV.setAdapter(adapter);
        adapter.setOnItemsChangedListener(new OrderDetailsItemsAdapter.OnQuantityChangedListener() {
            @Override
            public void onChanged(List<CartItem> items) {
                presenter.updateProducts(items);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return false;
            case R.id.action_edit:
                edit(true);
                return false;
            case R.id.action_submit:
                edit(false);
                presenter.updateOrder();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu, menu);
        submit = menu.getItem(0);
        edit = menu.getItem(1);
        edit.setVisible(false);
        return true;

    }

    @Override
    public void updateAllItems(List<CartItem> items) {
        adapter.addAllItems(items);
    }

    @Override
    public void updateView(String id, String creationDate, String status, String total) {
        this.creationDate.setText(creationDate);
        this.orderId.setText(id);
        this.orderStatus.setText(status);
        this.total.setText(total);
    }

    @Override
    public void edit(boolean edit) {
        if (edit) {
            this.edit.setVisible(false);
            submit.setVisible(true);
            adapter.setEditing(true);
        }
        else {
            submit.setVisible(false);
            this.edit.setVisible(true);
            adapter.setEditing(false);
        }
    }

    @Override
    public void addCoupon(String code, String value, String priceBeforeDiscount) {
        couponLayout.setVisibility(View.VISIBLE);
        orderCouponCode.setText(code);
        orderPriceBeforeDiscount.setText(priceBeforeDiscount);
        orderCouponValue.setText(value);
    }

    @OnClick(R.id.cancelOrderBtn)
    public void onCancelOrderBtnClicked() {
        if (presenter.checkOrderCancelOptionAvailable()) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle(R.string.confirm_delete_order_title)
                    .setMessage(R.string.confirm_delete_order_desc)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.cancelOrder();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        } else {
            showMessage(R.string.warn_cant_cancel_order);
        }
    }
}
