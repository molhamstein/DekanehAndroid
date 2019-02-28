package com.socket.dekaneh.adapter;

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

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.product_details.ProductDetailsActivity;
import com.socket.dekaneh.custom.DekanehInterpolator;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Product> products;
    private CacheStore cacheStore;
    private OnItemCountChange onItemCountChange;
    private int plusMinusAnimationBtnVal = 18;


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
            holder.expandingBtn.animate().scaleX(1.2f).setDuration(10).start();
            holder.plusOneBtn.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
            holder.minusOne.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
            holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
        } else {
            holder.orderNowBtn.setVisibility(View.VISIBLE);
            holder.orderBtn.setVisibility(View.GONE);
            holder.expandingBtn.animate().scaleX(1.0f).setDuration(1).start();
            holder.plusOneBtn.animate().translationX(0).setInterpolator(new DekanehInterpolator(1)).start();
            holder.minusOne.animate().translationX(0).setInterpolator(new DekanehInterpolator(1)).start();
        }

        if (product.isOffer()) {
            holder.offerTag.setVisibility(View.VISIBLE);
        }

        if (cacheStore.getSession().getClientType().equals(User.Type.horeca.toString())) {
            setPrice(holder, product.getHorecaPrice(), product.getHorecaPriceDiscount());
        } else {
            setPrice(holder, product.getWholeSalePrice(), product.getWholeSalePriceDiscount());
        }

        holder.name.setText(product.getNameAr());
        holder.pack.setText(product.getPack());
        if (product.getMedia() != null && !product.getMedia().getUrl().equals(""))
            Picasso.get().load(product.getMedia().getUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsActivity.start(view.getContext(), product);
            }
        });

        holder.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.getOfferMaxQuantity() <= cacheStore.cartItemCount(item)) {
                    ViewUtils.showToast(view.getContext(), view.getContext().getString(R.string.max_quantity_reached, cacheStore.cartItemCount(item)));
                } else {
                    view.setVisibility(View.GONE);
                    holder.orderBtn.setVisibility(View.VISIBLE);
                    cacheStore.addCartItem(item);
                    holder.expandingBtn.animate().scaleX(1.2f).start();
                    holder.plusOneBtn.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                    holder.minusOne.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                    holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                    if (onItemCountChange != null) {
                        onItemCountChange.onChange();
                    }
                }
            }
        });

        holder.plusOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.getOfferMaxQuantity() <= cacheStore.cartItemCount(item)) {
                    ViewUtils.showToast(view.getContext(), view.getContext().getString(R.string.max_quantity_reached, cacheStore.cartItemCount(item)));
                } else {
                    cacheStore.addCartItem(item);
                    holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                    if (onItemCountChange != null) {
                        onItemCountChange.onChange();
                    }
                }
            }
        });

        holder.minusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cacheStore.cartItemCount(item) <= 1) {
                    cacheStore.removeCartItem(item);
                    holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                    holder.expandingBtn.animate().scaleX(1).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            holder.orderNowBtn.setVisibility(View.VISIBLE);
                            holder.orderBtn.setVisibility(View.GONE);
                            holder.plusOneBtn.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).start();
                            holder.minusOne.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).start();
                        }
                    }).start();


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

    private void setPrice(SearchViewHolder holder, int price, int discount) {
        if (discount != 0 && discount != price) {
            holder.price.setText(String.valueOf(discount));
            holder.oldPrice.setText(String.valueOf(price));
        } else {
            holder.price.setText(String.valueOf(price));
            holder.productDiscount.setVisibility(View.GONE); // layout
        }
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
        @BindView(R.id.pack)
        TextView pack;
        @BindView(R.id.expandingBtn)
        View expandingBtn;
        @BindView(R.id.productDiscount)
        View productDiscount;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}
