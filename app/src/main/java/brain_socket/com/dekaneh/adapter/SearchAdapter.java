package brain_socket.com.dekaneh.adapter;

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
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Product> products;
    private CacheStore cacheStore;
    private OnItemCountChange onItemCountChange;


    @Inject
    public SearchAdapter(CacheStore cacheStore) {
        products = new ArrayList<>();
        this.cacheStore = cacheStore;
    }

    public void setOnItemCountChange(OnItemCountChange onItemCountChange) {
        this.onItemCountChange = onItemCountChange;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, int position) {

        final Product product = products.get(position);

        final CartItem item = new CartItem(product);

        if (cacheStore.isCartItemExist(item)) {
            holder.orderNowBtn.setVisibility(View.GONE);
            holder.orderBtn.setVisibility(View.VISIBLE);
            holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
        }

        holder.price.setText(String.valueOf(product.getRetailPrice()));
        holder.name.setText(product.getNameAr());
        if (!item.getMedia().getUrl().equals(""))
            Picasso.get().load(product.getMedia().getUrl()).into(holder.image);
        holder.oldPrice.setText(String.valueOf(product.getMarketPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsActivity.start(view.getContext(), product);
            }
        });

        holder.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                holder.orderBtn.setVisibility(View.VISIBLE);
                cacheStore.addCartItem(item);
                holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                if (onItemCountChange != null) {
                    onItemCountChange.onChange();
                }
            }
        });

        holder.plusOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheStore.addCartItem(item);
                holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                if (onItemCountChange != null) {
                    onItemCountChange.onChange();
                }

            }
        });

        holder.minusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cacheStore.cartItemCount(item) <= 1) {
                    cacheStore.removeCartItem(item);
                    holder.orderNowBtn.setVisibility(View.VISIBLE);
                    holder.orderBtn.setVisibility(View.GONE);
                } else {
                    cacheStore.removeCartItem(item);
                    holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                }
                if (onItemCountChange != null) {
                    onItemCountChange.onChange();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addAllProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void clear() {
        this.products.clear();
        notifyDataSetChanged();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productPrice)
        TextView price;
        @BindView(R.id.oldPrice)
        TextView oldPrice;
        @BindView(R.id.productOfferTag)
        View offerTag;
        @BindView(R.id.productName)
        TextView name;
        @BindView(R.id.productImage)
        ImageView image;
        @BindView(R.id.orderNowBtn)
        View orderNowBtn;
        @BindView(R.id.orderBtn)
        View orderBtn;
        @BindView(R.id.plusOne)
        View plusOneBtn;
        @BindView(R.id.minusOne)
        View minusOne;
        @BindView(R.id.orderCount)
        TextView orderCount;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}
