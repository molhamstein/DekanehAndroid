package brain_socket.com.dekaneh.activity.order_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.CartOrdersAdapter;
import brain_socket.com.dekaneh.adapter.OrderDetailsItemsAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Order;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.utils.GsonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return false;
            case R.id.action_edit:
                edit.setVisible(false);
                submit.setVisible(true);
                adapter.setEditing(true);
                return false;
            case R.id.action_submit:
                submit.setVisible(false);
                edit.setVisible(true);
                adapter.setEditing(false);
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu, menu);
        submit = menu.getItem(0);
        edit = menu.getItem(1);
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
}
