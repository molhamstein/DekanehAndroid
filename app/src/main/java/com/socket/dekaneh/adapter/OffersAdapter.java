package com.socket.dekaneh.adapter;

import android.content.Context;
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

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.product_details.ProductDetailsActivity;
import com.socket.dekaneh.custom.DekanehInterpolator;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.Session;
import com.socket.dekaneh.network.model.CartItem;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.User;
import com.socket.dekaneh.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {


    private List<Offer> offers;
    private int itemLayoutId;
    private CacheStore cacheStore;
    private OnItemCountChange onItemCountChange;
    private int plusMinusAnimationBtnVal = 15;

    @Inject
    public OffersAdapter(int itemLayoutId, Context context) {
        this.offers = new ArrayList<>();
        this.itemLayoutId = itemLayoutId;
        cacheStore = new CacheStore(context, new Session(context));
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OffersViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
    }

    public void setOnItemCountChange(OnItemCountChange onItemCountChange) {
        this.onItemCountChange = onItemCountChange;
    }

    @Override
    public void onBindViewHolder(@NonNull final OffersViewHolder holder, int position) {

        final Offer offer = offers.get(position);
        offer.setId(offer.getId()); //TODO : change

        final Product product = new Product(offer, offer.getMedia());
        final CartItem item = new CartItem(offer);

        for (CartItem mItem : cacheStore.getCartItems())
            if (mItem.getId().equals(item.getId())) {
                holder.orderNowBtn.setVisibility(View.GONE);
                holder.orderBtn.setVisibility(View.VISIBLE);
                holder.expandingBtn.animate().scaleX(1.2f).setDuration(10).start();
                holder.plusOneBtn.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                holder.minusOne.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                holder.orderCount.setText(String.valueOf(mItem.getCount()));
            }

        if (offer.getMedia() != null && !offer.getMedia().getUrl().equals(""))
            Picasso.get().load(offer.getMedia().getUrl()).into(holder.image);
        holder.name.setText(offer.getNameAr());
        holder.percent.setText(offer.getPercentageString(cacheStore.getSession().getClientType()));
        holder.pack.setText(String.valueOf(offer.getPack()));

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
                    holder.expandingBtn.animate().scaleX(1).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            holder.orderNowBtn.setVisibility(View.VISIBLE);
                            holder.orderBtn.setVisibility(View.GONE);
                            holder.plusOneBtn.animate().translationX(-ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
                            holder.minusOne.animate().translationX(ViewUtils.getPXSize(plusMinusAnimationBtnVal, holder.itemView.getContext())).setInterpolator(new DekanehInterpolator(1)).start();
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsActivity.start(view.getContext(), product);
            }
        });

        if (cacheStore.getSession().getClientType().equals(User.Type.horeca.toString())) {
            setPrice(holder, product.getHorecaPrice(), product.getHorecaPriceDiscount());
        } else {
            setPrice(holder, product.getWholeSalePrice(), product.getWholeSalePriceDiscount());
        }

    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public void addAllOffers(List<Offer> offers) {
        this.offers = offers;
        notifyDataSetChanged();
    }

    private void setPrice(OffersViewHolder holder, int price, int discount) {
        Log.d("QWEQWE", "setPrice: " + discount);
        if (discount != 0) {
            holder.price.setText(String.valueOf(discount));
            holder.oldPrice.setText(String.valueOf(price));
        } else {
            holder.offerDiscount.setVisibility(View.GONE);
            holder.price.setText(String.valueOf(price));
        }
    }


    class OffersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.offerImage)
        ImageView image;
        @BindView(R.id.offerName)
        TextView name;
        @BindView(R.id.percent)
        TextView percent;
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
        @BindView(R.id.productPrice)
        TextView price;
        @BindView(R.id.oldPrice)
        TextView oldPrice;
        @BindView(R.id.expandingBtn)
        View expandingBtn;
        @BindView(R.id.offerDiscount)
        View offerDiscount;
        @BindView(R.id.pack)
        TextView pack;

        OffersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
