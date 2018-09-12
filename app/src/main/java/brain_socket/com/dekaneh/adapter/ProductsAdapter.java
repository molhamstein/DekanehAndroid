package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter {

    private List<Product> products;
    private final int SEE_MORE_VIEW = -1;
    private final int MAX_NUM_OF_PRODUCTS = 8;


    public ProductsAdapter(List<Product> products) {
        this.products = products;
        if (products.size() >= MAX_NUM_OF_PRODUCTS) {
            products.add(null);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SEE_MORE_VIEW)
            return new SeeMoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_see_more, parent, false));
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ProductViewHolder) {

            ProductViewHolder productViewHolder = (ProductViewHolder) holder;

            final Product product = products.get(position);
            productViewHolder.price.setText(product.getRetailPrice());
            productViewHolder.name.setText(product.getNameAr());
            Picasso.get().load(product.getImage()).into(productViewHolder.image);
            if (product.isHasOffer()) {
                productViewHolder.offerTag.setVisibility(View.VISIBLE);
            } else {
                productViewHolder.offerTag.setVisibility(View.GONE);
            }

            productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductDetailsActivity.start(view.getContext(), product);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (products.size() >= MAX_NUM_OF_PRODUCTS)
            return products.get(position) != null ? position : SEE_MORE_VIEW;
        return super.getItemViewType(position);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productPrice)
        TextView price;
        @BindView(R.id.productOfferTag)
        View offerTag;
        @BindView(R.id.productName)
        TextView name;
        @BindView(R.id.productImage)
        ImageView image;

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SeeMoreViewHolder extends RecyclerView.ViewHolder {

        public SeeMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
