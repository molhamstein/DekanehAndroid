package com.socket.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.product_details.ProductDetailsActivity;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.model.Offer;
import com.socket.dekaneh.network.model.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MiniOfferAdapter extends RecyclerView.Adapter<MiniOfferAdapter.MiniOfferViewHolder> {

    private List<Offer> offers;
    private CacheStore cacheStore;

    @Inject
    public MiniOfferAdapter(CacheStore cacheStore) {
        offers = new ArrayList<>();
        this.cacheStore = cacheStore;
    }

    @NonNull
    @Override
    public MiniOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MiniOfferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mini_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MiniOfferViewHolder holder, int position) {

        final Offer offer = offers.get(position);

        holder.body.setText(offer.getNameAr());
        if (offer.getPercentageString(cacheStore.getSession().getClientType()).equals("0%")) {
            holder.percent.setText("%");
        }else {
            holder.percent.setText(offer.getPercentageString(cacheStore.getSession().getClientType()));
        }
        if (cacheStore.getSession().getClientType().equals(User.Type.horeca.toString())) {
            setPrice(holder, offer.getHorecaPrice(), offer.getHorecaPriceDiscount());
        } else {
            setPrice(holder, offer.getWholeSalePrice(), offer.getWholeSalePriceDiscount());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsActivity.startAsOffer(view.getContext(), offer, offer.getPercentageString(cacheStore.getSession().getClientType()));
            }
        });

    }

    private void setPrice(MiniOfferViewHolder holder, int price, int discount) {
        if (discount != 0) {
            holder.newPrice.setText(String.valueOf(discount));
            holder.oldPrice.setText(String.valueOf(price));
        } else {
            holder.oldPrice.setVisibility(View.GONE);
            holder.newPrice.setText(String.valueOf(price));
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

    class MiniOfferViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.percent)
        TextView percent;
        @BindView(R.id.body)
        TextView body;
        @BindView(R.id.oldPrice)
        TextView oldPrice;
        @BindView(R.id.newPrice)
        TextView newPrice;

        public MiniOfferViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
