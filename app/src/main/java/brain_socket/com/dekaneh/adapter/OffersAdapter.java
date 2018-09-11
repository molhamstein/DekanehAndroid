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

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.model.Offer;
import brain_socket.com.dekaneh.network.model.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersAdapter  extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder>{


    private List<Offer> offers;
    private int itemLayoutId;

    public OffersAdapter(int itemLayoutId) {
        this.offers = new ArrayList<>();
        this.itemLayoutId = itemLayoutId;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OffersViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {

        Offer offer = offers.get(position);

        Picasso.get().load(offer.getImage()).into(holder.image);
        holder.name.setText(offer.getNameAr());
        holder.percent.setText("10%");

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

        OffersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
