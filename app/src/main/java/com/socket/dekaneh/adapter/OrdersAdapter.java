package com.socket.dekaneh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.order_details.OrderDetailsActivity;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Order;
import com.socket.dekaneh.utils.AppDateUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private List<Order> orders;
    private CacheStore cacheStore;

    @Inject
    public OrdersAdapter(Context context) {
//        this.cacheStore = cacheStore;
        orders = new ArrayList<>();
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {

        final Order order = orders.get(position);

        holder.date.setText(AppDateUtils.dateToString(order.getOrderDate()));
        holder.id.setText(order.getId());
        holder.price.setText(String.valueOf(order.getTotalPrice()));
        switch (order.getStatus()) {
            case pending:
                holder.status.setText(R.string.pending);
                break;
            case delivered:
                holder.status.setText(R.string.delivered);
                break;
            case canceled:
                holder.status.setText(R.string.canceled);
                break;
            case inDelivery:
                holder.status.setText(R.string.inDelivery);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailsActivity.start(view.getContext(), order);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void addAllOrderes(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    class OrdersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.orderDate)
        TextView date;
        @BindView(R.id.orderId)
        TextView id;
        @BindView(R.id.orderPrice)
        TextView price;
        @BindView(R.id.orderStatus)
        TextView status;

        OrdersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
