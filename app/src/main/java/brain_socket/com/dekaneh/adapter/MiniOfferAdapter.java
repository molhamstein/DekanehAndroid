package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.model.Offer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MiniOfferAdapter extends RecyclerView.Adapter<MiniOfferAdapter.MiniOfferViewHolder> {

    private List<Offer> offers;

    public MiniOfferAdapter() {
        offers = new ArrayList<>();
    }

    @NonNull
    @Override
    public MiniOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MiniOfferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mini_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MiniOfferViewHolder holder, int position) {

        final Offer offer = offers.get(position);

        holder.percent.setText(offer.getPercentageString());
        holder.body.setText(offer.getDescription());
//        holder.oldPrice.setText(String.valueOf(offer.getMarketPrice()));
//        holder.newPrice.setText(String.valueOf(offer.getRetailPrice()));

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
