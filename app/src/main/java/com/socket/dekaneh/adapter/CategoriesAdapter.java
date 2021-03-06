package com.socket.dekaneh.adapter;

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

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.category_details.CategoryDetailsActivity;
import com.socket.dekaneh.network.model.Category;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> categories;

    @Inject
    public CategoriesAdapter() {
        this.categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        final Category category = categories.get(position);

        holder.title.setText(category.getTitleAr());
        if (category.getIcon() != null && !category.getIcon().equals(""))
            Picasso.get().load(category.getIcon()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDetailsActivity.start(view.getContext(), category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addAllCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_title)
        TextView title;
        @BindView(R.id.categoryImage)
        ImageView image;


        CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
