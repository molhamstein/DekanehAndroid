package brain_socket.com.dekaneh.activity.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.CartOrdersAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import brain_socket.com.dekaneh.network.model.CartItem;
import butterknife.BindView;
import butterknife.ButterKnife;

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

        ordersRV.setLayoutManager(new LinearLayoutManager(this));
        ordersRV.setAdapter(cartAdapter);
        presenter.fetchItems();
        cartAdapter.setOnQuantityChangedListener(new CartOrdersAdapter.OnQuantityChangedListener() {
            @Override
            public void onChanged(int price) {
                total.setText(String.valueOf(price));
            }
        });
        total.setText(String.valueOf(cartAdapter.getPrice()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
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
}