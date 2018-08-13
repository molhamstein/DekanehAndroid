package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brain_socket.com.dekaneh.R;

public class MiniOfferAdapter extends RecyclerView.Adapter<MiniOfferAdapter.MiniOfferViewHolder> {


    @NonNull
    @Override
    public MiniOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MiniOfferViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mini_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MiniOfferViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class MiniOfferViewHolder extends RecyclerView.ViewHolder{

        public MiniOfferViewHolder(View itemView) {
            super(itemView);
        }
    }
}
