package com.socket.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.socket.dekaneh.R;
import com.socket.dekaneh.fragment.profile.ProfileFragmentVP;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.socket.dekaneh.network.model.Award;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RewardsAdapter extends  RecyclerView.Adapter<RewardsAdapter.RewardViewHolder> {

    ProfileFragmentVP.View pView ;
    private List<Award> data ;

    @Inject
    public RewardsAdapter(ProfileFragmentVP.View pView){
        this.pView  = pView;
        data = new ArrayList<>();
    }

    public void addAwards(List<Award> data){
        this.data = data ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new RewardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reward, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pView.openDialog("Dialog");
//            }
//        });
        holder.title.setText(Locale.getDefault().equals("ar")?data.get(position).getNameAr():data.get(position).getNameEn());
        holder.body.setText(data.get(position).getDetails());
        holder.date.setText(data.get(position).getTo());
        holder.progressBar.setMax(100);
//        holder.progressBar.setMin(0);
        holder.progressBar.setProgress(data.get(position).getProgressPercentage());
        holder.progressValue.setText(data.get(position).getProgressPercentage().toString()+"/100");

    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class RewardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title ;

        @BindView(R.id.body)
        TextView body ;

        @BindView(R.id.validTill)
        TextView validTill ;

        @BindView(R.id.date)
        TextView date ;

        @BindView(R.id.progressValue)
        TextView progressValue ;


        @BindView(R.id.progressBar)
        ProgressBar progressBar ;


        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
