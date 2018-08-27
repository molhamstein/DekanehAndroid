package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brain_socket.com.dekaneh.R;

public class CartOrdersAdapter extends RecyclerView.Adapter<CartOrdersAdapter.CartOrderViewHolder>{

    @NonNull
    @Override
    public CartOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartOrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartOrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class CartOrderViewHolder extends RecyclerView.ViewHolder {

        public CartOrderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
