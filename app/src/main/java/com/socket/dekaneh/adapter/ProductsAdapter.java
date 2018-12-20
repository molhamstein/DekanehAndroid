package com.socket.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.category_details.CategoryDetailsActivity;
import com.socket.dekaneh.activity.product_details.ProductDetailsActivity;
import com.socket.dekaneh.custom.DekanehInterpolator;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter {

    private final int SEE_MORE_VIEW = -1;
    private final int MAX_NUM_OF_PRODUCTS = 9;
    private List<Product> products;
    private CacheStore cacheStore;
    private Category category;
    private OnItemCountChange onItemCountChange;
    private int plusMinusAnimationBtnVal = 18;

    @Inject
    public ProductsAdapter(CacheStore cacheStore) {
        this.cacheStore = cacheStore;
        this.products = new ArrayList<>();
    }

    public ProductsAdapter(List<Product> products, CacheStore cacheStore, Category category) {
        this.products = products;
        Log.d("CategoryDetailsPresent", "onBindViewHolder: size = " + products.size());
//        if (products.size() >= MAX_NUM_OF_PRODUCTS) {
//            products.add(new Product());
//        }
        this.cacheStore = cacheStore;
        this.category = category;
    }

    public ProductsAdapter(List<Product> products, CacheStore cacheStore, Category category, boolean seeMore) {
        this.products = products;
        Log.d("CategoryDetailsPresent", "onBindViewHolder: size = " + products.size());
//        if (products.size() >= MAX_NUM_OF_PRODUCTS && seeMore) {
//            products.add(new Product());
//        }
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

        holder.setIsRecyclable(false);
        if (holder instanceof ProductViewHolder) {
            final Product product = products.get(position);

            final ProductViewHolder productViewHolder = (ProductViewHolder) holder;

            final CartItem item = new CartItem(product);

            if (product.hasDiscount(cacheStore.getSession().getClientType())) {
                productViewHolder.productOfferTagLayout.setVisibility(View.VISIBLE);
                productViewHolder.offerTag.setTextSize(16);
                productViewHolder.productOfferTagImage.setImageResource(R.drawable.badge_discount);
                productViewHolder.offerTag.setText(product.getPercentageString(cacheStore.getSession().getClientType()));
            } else if (!product.getOffersIds().isEmpty()) {
                productViewHolder.productOfferTagLayout.setVisibility(View.VISIBLE);
            }

            if (cacheStore.isCartItemExist(item)) {
                productViewHolder.orderNowBtn.setVisibility(View.GONE);
                productViewHolder.orderBtn.setVisibility(View.VISIBLE);
                ((ProductViewHolder) holder).expandingBtn.animate().scaleX(1.2f).setDuration(10).start();
                ((ProductViewHolder) holder).plusOneBtn.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                ((ProductViewHolder) holder).minusOne.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                productViewHolder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
            }

            if (cacheStore.getSession().getClientType().equals(User.Type.horeca.toString())) {
                setPrice(productViewHolder, product.getHorecaPrice(), product.getHorecaPriceDiscount());
            } else {
                setPrice(productViewHolder, product.getWholeSalePrice(), product.getWholeSalePriceDiscount());
            }

            productViewHolder.name.setText(product.getNameAr());
            productViewHolder.pack.setText(product.getPack());
            if (product.getMedia() != null && !product.getMedia().getUrl().equals(""))
                Picasso.get().load(product.getMedia().getThumbnail()).into(productViewHolder.image);

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
                    ((ProductViewHolder) holder).expandingBtn.animate().scaleX(1.2f).start();
                    ((ProductViewHolder) holder).plusOneBtn.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                    ((ProductViewHolder) holder).minusOne.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
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
                        productViewHolder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
                        ((ProductViewHolder) holder).expandingBtn.animate().scaleX(1).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                ((ProductViewHolder) holder).orderNowBtn.setVisibility(View.VISIBLE);
                                ((ProductViewHolder) holder).orderBtn.setVisibility(View.GONE);
                                ((ProductViewHolder) holder).plusOneBtn.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).start();
                                ((ProductViewHolder) holder).minusOne.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).start();
                            }
                        }).start();


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
        return products.size() >= MAX_NUM_OF_PRODUCTS ? products.size() + 1 : products.size();
    }

    public void addAllProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (products.size() >= MAX_NUM_OF_PRODUCTS)
            return position < products.size() ? position : SEE_MORE_VIEW;
        return super.getItemViewType(position);
    }

    private void setPrice(ProductViewHolder holder, int price, int discount) {
        if (discount != 0 && discount != price) {
            holder.price.setText(String.valueOf(discount));
            holder.oldPrice.setText(String.valueOf(price));
        } else {
            holder.price.setText(String.valueOf(price));
            holder.productDiscount.setVisibility(View.GONE); // layout
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productPrice)
        TextView price;
        @BindView(R.id.oldPrice)
        TextView oldPrice;
        @BindView(R.id.productOfferTag)
        TextView offerTag;
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
        @BindView(R.id.productOfferTagLayout)
        View productOfferTagLayout;
        @BindView(R.id.productOfferTagImage)
        ImageView productOfferTagImage;

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
