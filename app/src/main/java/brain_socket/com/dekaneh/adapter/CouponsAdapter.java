package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.model.Coupon;
import brain_socket.com.dekaneh.utils.AppDateUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.CouponsViewHolder> {

    private List<Coupon> coupons;
    private OnCouponClick onCouponClick;

    @Inject
    public CouponsAdapter() {
        this.coupons = new ArrayList<>();
    }

    @NonNull
    @Override
    public CouponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CouponsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CouponsViewHolder holder, final int position) {

        final Coupon coupon = coupons.get(position);
        if (coupon.getType() == Coupon.Type.fixed) holder.fixed.setVisibility(View.VISIBLE);
        else holder.percent.setVisibility(View.VISIBLE);

        holder.couponValue.setText(String.valueOf(coupon.getValue()));
        holder.couponCode.setText(coupon.getCode());
        holder.couponExpiryDate.setText(AppDateUtils.dateToString(coupon.getExpireDate()));
        holder.couponUsage.setText(String.format("%s / %s", String.valueOf(coupon.getNumberOfUsed()), String.valueOf(coupon.getNumberOfTimes())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCouponClick != null) {
                    onCouponClick.onClick(coupon, holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public void addAllCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
        notifyDataSetChanged();
    }

    public void setOnCouponClick(OnCouponClick onCouponClick) {
        this.onCouponClick = onCouponClick;
    }

    public class CouponsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.couponValue)
        TextView couponValue;
        @BindView(R.id.couponCode)
        TextView couponCode;
        @BindView(R.id.couponExpiryDate)
        TextView couponExpiryDate;
        @BindView(R.id.couponUsage)
        TextView couponUsage;
        @BindView(R.id.percent)
        View percent;
        @BindView(R.id.fixed)
        View fixed;

        public CouponsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCouponClick {
        void onClick(Coupon coupon, int position);
    }
}
