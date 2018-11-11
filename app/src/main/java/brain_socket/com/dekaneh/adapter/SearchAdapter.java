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
import brain_socket.com.dekaneh.custom.DekanehInterpolator;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.User;
import brain_socket.com.dekaneh.utils.ViewUtils;
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
        }

        if (cacheStore.getSession().getClientType().equals(User.Type.retailCostumer.toString())) {
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
        holder.price.setText(String.valueOf(price));
        if (discount != 0) {
            holder.oldPrice.setText(String.valueOf(discount));
        } else {
            holder.oldPrice.setVisibility(View.GONE);
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
