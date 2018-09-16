package brain_socket.com.dekaneh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.CartItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartOrdersAdapter extends RecyclerView.Adapter<CartOrdersAdapter.CartOrderViewHolder>{

    private List<CartItem> items;
    private CacheStore cacheStore;
    private OnQuantityChangedListener onQuantityChangedListener;


    @Inject
    public CartOrdersAdapter(Context context) {
        items = new ArrayList<>();
        cacheStore = new CacheStore(context);
    }

    @NonNull
    @Override
    public CartOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartOrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartOrderViewHolder holder, int position) {

        final CartItem item = items.get(position);

        Picasso.get().load(item.getImage()).into(holder.image);
        holder.name.setText(item.getNameAr());
        holder.quantity.setText(String.valueOf(item.getRetailPrice()) + " * " + String.valueOf(item.getCount()));
        holder.accPrice.setText(String.valueOf(item.getRetailPrice() * item.getCount()));
        holder.orderCount.setText(String.valueOf(item.getCount()));


        holder.plusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheStore.addCartItem(item);
                item.addOne();
                notifyDataSetChanged();
                if (onQuantityChangedListener != null)
                    onQuantityChangedListener.onChanged(getPrice());
            }
        });

        holder.minusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheStore.removeCartItem(item);
                item.removeOne();
                notifyDataSetChanged();
                if (onQuantityChangedListener != null)
                    onQuantityChangedListener.onChanged(getPrice());
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

    public int getPrice() {
        int price = 0;
        for (CartItem item : items) {
            price += item.getWholePrice();
        }
        return price;
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
        void onChanged(int price);
    }
}
