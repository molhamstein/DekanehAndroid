package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.category_details.CategoryDetailsActivity;
import brain_socket.com.dekaneh.activity.manufacturer.ManufacturerActivity;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Category;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter {

    private final int SEE_MORE_VIEW = -1;
    private final int MAX_NUM_OF_PRODUCTS = 8;
    private List<Product> products;
    private CacheStore cacheStore;
    private Category category;
    private OnItemCountChange onItemCountChange;

    public ProductsAdapter(CacheStore cacheStore) {
        this.cacheStore = cacheStore;
        this.products = new ArrayList<>();
    }

    public ProductsAdapter(List<Product> products, CacheStore cacheStore, Category category) {
        this.products = products;
        if (products.size() >= MAX_NUM_OF_PRODUCTS) {
            products.add(null);
        }
        this.cacheStore = cacheStore;
        this.category = category;
    }

    public void setOnItemCountChange(OnItemCountChange onItemCountChange) {
        this.onItemCountChange = onItemCountChange;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SEE_MORE_VIEW)
            return new SeeMoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_see_more, parent, false));
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final Product product = products.get(position);
        if (holder instanceof ProductViewHolder) {

            final ProductViewHolder productViewHolder = (ProductViewHolder) holder;

            final CartItem item = new CartItem(product);

            if (cacheStore.isCartItemExist(item)) {
                productViewHolder.orderNowBtn.setVisibility(View.GONE);
                productViewHolder.orderBtn.setVisibility(View.VISIBLE);
                productViewHolder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
            }

            productViewHolder.price.setText(String.valueOf(product.getRetailPrice()));
            productViewHolder.name.setText(product.getNameAr());
            Picasso.get().load(product.getImage()).into(productViewHolder.image);
            productViewHolder.oldPrice.setText(String.valueOf(product.getMarketPrice()));

            productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductDetailsActivity.start(view.getContext(), product);
                }
            });

            productViewHolder.orderNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.GONE);
                    productViewHolder.orderBtn.setVisibility(View.VISIBLE);
                    cacheStore.addCartItem(item);
                    productViewHolder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                    if (onItemCountChange != null) {
                        onItemCountChange.onChange();
                    }
                }
            });

            productViewHolder.plusOneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cacheStore.addCartItem(item);
                    productViewHolder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                    if (onItemCountChange != null) {
                        onItemCountChange.onChange();
                    }

                }
            });

            productViewHolder.minusOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cacheStore.cartItemCount(item) <= 1) {
                        cacheStore.removeCartItem(item);
                        ((ProductViewHolder) holder).orderNowBtn.setVisibility(View.VISIBLE);
                        ((ProductViewHolder) holder).orderBtn.setVisibility(View.GONE);
                    } else {
                        cacheStore.removeCartItem(item);
                        productViewHolder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                    }
                    if (onItemCountChange != null) {
                        onItemCountChange.onChange();
                    }
                }
            });


        } else if (holder instanceof SeeMoreViewHolder) {
            final SeeMoreViewHolder seeMoreViewHolder = (SeeMoreViewHolder) holder;
            seeMoreViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!products.isEmpty())
                        CategoryDetailsActivity.start(view.getContext(), category);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addAllProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
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
