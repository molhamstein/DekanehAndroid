package brain_socket.com.dekaneh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.Session;
import brain_socket.com.dekaneh.network.model.CartItem;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Order;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {


    private List<Offer> offers;
    private int itemLayoutId;
    private CacheStore cacheStore;

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

    @Override
    public void onBindViewHolder(@NonNull final OffersViewHolder holder, int position) {

        final Offer offer = offers.get(position);
        final CartItem item = new CartItem(offer);

        for (CartItem mItem : cacheStore.getCartItems())
            if (mItem.getId().equals(item.getId())) {
                holder.orderNowBtn.setVisibility(View.GONE);
                holder.orderBtn.setVisibility(View.VISIBLE);
                holder.orderCount.setText(String.valueOf(mItem.getCount()));
            }

        if (offer.getProducts() != null && !offer.getProducts().isEmpty())
            Picasso.get().load(offer.getProducts().get(0).getImage()).into(holder.image);
        holder.name.setText(offer.getNameAr());
        holder.percent.setText(offer.getPercentageString());

        holder.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                holder.orderBtn.setVisibility(View.VISIBLE);
                cacheStore.addCartItem(item);
                holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));
            }
        });

        holder.plusOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheStore.addCartItem(item);
                holder.orderCount.setText(String.valueOf(cacheStore.cartItemCount(item)));

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

            }
        });

    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public void addAllOffers(List<Offer> offers) {
        this.offers = offers;
        notifyDataSetChanged();
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

        OffersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
