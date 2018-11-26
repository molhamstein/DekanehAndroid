package com.socket.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.network.model.SubCategory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoriesViewHolder> {

    private List<SubCategory> subCategories;
    private OnSubCategoryClickListener onSubCategoryClickListener;

    @Inject
    public SubCategoriesAdapter() {
        this.subCategories = new ArrayList<>();
    }

    @NonNull
    @Override
    public SubCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SubCategoriesViewHolder holder, int position) {
        final SubCategory subCategory = subCategories.get(position);

        if (subCategory.isSelected()) {
            holder.title.setBackgroundResource(R.drawable.background_sub_category_textview_selected);
            holder.title.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));

        }
        else {
            holder.title.setBackgroundResource(R.drawable.background_sub_category_textview);
            holder.title.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.primaryText));

        }
        holder.title.setText(subCategory.getTitleAr());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                subCategory.setSelected(true);
                notifyDataSetChanged();
                if (onSubCategoryClickListener != null) {
                    onSubCategoryClickListener.onClick(subCategory.getId());
                }
            }
        });

    }

    private void deselectAll() {
        for (SubCategory sub : this.subCategories) {
            sub.setSelected(false);
        }
    }

    public void setOnSubCategoryClickListener(OnSubCategoryClickListener onSubCategoryClickListener) {
        this.onSubCategoryClickListener = onSubCategoryClickListener;
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public void addAllSubCategories(List<SubCategory> subCategories) {
        SubCategory subCategory = new SubCategory("الكل");
        subCategory.setSelected(true);
        this.subCategories = subCategories;
        this.subCategories.add(0, subCategory);
        notifyDataSetChanged();
    }

    class SubCategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemSubCatTitle)
        TextView title;

        public SubCategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



    public interface OnSubCategoryClickListener {

        void onClick(String subCategoryId);

    }

}
