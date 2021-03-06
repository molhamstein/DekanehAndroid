package com.socket.dekaneh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.socket.dekaneh.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.base.BaseActivity;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.Session;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartOrdersAdapter extends RecyclerView.Adapter<CartOrdersAdapter.CartOrderViewHolder>{

    private List<CartItem> items;
    private CacheStore cacheStore;
    private OnQuantityChangedListener onQuantityChangedListener;


    @Inject
    public CartOrdersAdapter(Context context) {
        items = new ArrayList<>();
        cacheStore = new CacheStore(context, new Session(context));
    }

    @NonNull
    @Override
    public CartOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartOrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CartOrderViewHolder holder, int position) {

        final CartItem item = items.get(position);
        if (item.getMedia() != null && !item.getMedia().getUrl().equals(""))
            Picasso.get().load(item.getMedia().getThumbnail()).into(holder.image);
        holder.name.setText(item.getNameAr());
        holder.quantity.setText(String.valueOf(item.getPrice(cacheStore.getSession().getClientType().equals(User.Type.horeca.toString()))) + " * " + String.valueOf(item.getCount()));
        holder.accPrice.setText(String.valueOf(item.getTotalPrice(cacheStore.getSession().getClientType().equals(User.Type.horeca.toString()))));
        holder.orderCount.setText(String.valueOf(item.getCount()));


        holder.plusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getOfferMaxQuantity() <= cacheStore.cartItemCount(item)) {
                    ViewUtils.showToast(view.getContext(), view.getContext().getString(R.string.max_quantity_reached, cacheStore.cartItemCount(item)));
                } else {
                    cacheStore.addCartItem(item);
                    item.addOne();
                    notifyDataSetChanged();
                    if (onQuantityChangedListener != null)
                        onQuantityChangedListener.onChanged(items.isEmpty());
                }
            }
        });

        holder.minusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    cacheStore.removeCartItem(item);
                    item.removeOne();
                    notifyDataSetChanged();

                    if (item.getCount() <= 0) {
                        items.remove(item);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                ((BaseActivity) holder.itemView.getContext()).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        notifyDataSetChanged();
                                    }
                                });
                            }
                        }, 500);
                    }

                    if (onQuantityChangedListener != null)
                        onQuantityChangedListener.onChanged(items.isEmpty());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAllItems(List<CartItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnQuantityChangedListener(OnQuantityChangedListener onQuantityChangedListener) {
        this.onQuantityChangedListener = onQuantityChangedListener;
    }

    class CartOrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemImage)
        ImageView image;
        @BindView(R.id.itemName)
        TextView name;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.accPrice)
        TextView accPrice;
        @BindView(R.id.plusOne)
        View plusOne;
        @BindView(R.id.minusOne)
        View minusOne;
        @BindView(R.id.orderCount)
        TextView orderCount;

        public CartOrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnQuantityChangedListener{
        void onChanged(boolean isCartClear);
    }
}
