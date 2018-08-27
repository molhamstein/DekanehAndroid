package brain_socket.com.dekaneh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.CartOrdersAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends BaseActivity {

    @BindView(R.id.cartOrderRV)
    RecyclerView ordersRV;

    public static void start(Context context) {
        Intent starter = new Intent(context, CartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        ordersRV.setLayoutManager(new LinearLayoutManager(this));
        ordersRV.setAdapter(new CartOrdersAdapter());

    }
}
