package com.socket.dekaneh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.category_details.CategoryDetailsActivity;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.Session;
import com.socket.dekaneh.network.model.Category;
import com.socket.dekaneh.network.model.HomeCategory;
import com.socket.dekaneh.network.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder> {

    private CacheStore cacheStore;
    private List<HomeCategory> categories;
    private OnItemCountChange onItemCountChange;
    private HomeCategory featuredCategory;


    @Inject
    public HomeCategoriesAdapter(Context context) {
        this.categories = new ArrayList<>();
        this.cacheStore = new CacheStore(context, new Session(context));
    }

    @NonNull
    @Override
    public HomeCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_product_section, parent, false));
    }

    public void setOnItemCountChange(OnItemCountChange onItemCountChange) {
        this.onItemCountChange = onItemCountChange;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoriesViewHolder holder, int position) {

        final HomeCategory category = categories.get(position);
        holder.header.setText(category.getTitleAr());
        ProductsAdapter adapter;
        if (category.getId().isEmpty()){
            adapter = new ProductsAdapter(category.getProducts(), cacheStore ,category, false);
        } else {
            adapter = new ProductsAdapter(category.getProducts(), cacheStore ,category);
        }
        adapter.setOnItemCountChange(new OnItemCountChange() {
            @Override
            public void onChange() {
                if (onItemCountChange != null) {
                    onItemCountChange.onChange();
                }
            }
        });
        holder.productsRV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.productsRV.setAdapter(adapter);

        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDetailsActivity.start(view.getContext(), category);
            }
        });
        holder.seeAllText.setOnClickListener(new View.OnClickListener() {
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

    public void addAllCategories(List<HomeCategory> categories) {
        this.categories = categories;
        if (!this.categories.get(0).getId().isEmpty()) {
            this.categories.add(0, featuredCategory);
        }
        notifyDataSetChanged();
    }

    public void addFeaturedProducts(List<Product> products) {
        HomeCategory category = new HomeCategory();
        category.setId("");
        category.setTitleAr("المنتجات المميزة"); //TODO change to res
        category.setTitleEn("Featured Products");
        category.setProducts(products);
        this.featuredCategory = category;
    }

    class HomeCategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productSectionHeader)
        TextView header;
        @BindView(R.id.mainProductsRV)
        RecyclerView productsRV;
        @BindView(R.id.seeAllText)
        View seeAllText;

        HomeCategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
