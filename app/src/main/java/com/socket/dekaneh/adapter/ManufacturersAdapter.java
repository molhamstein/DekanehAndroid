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
import com.socket.dekaneh.activity.manufacturer.ManufacturerActivity;
import com.socket.dekaneh.network.CacheStore;
import com.socket.dekaneh.network.Session;
import com.socket.dekaneh.network.model.Manufacturer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ManufacturersAdapter extends RecyclerView.Adapter<ManufacturersAdapter.HomeCategoriesViewHolder> {

    private CacheStore cacheStore;
    private List<Manufacturer> manufacturers;


    @Inject
    public ManufacturersAdapter(Context context) {
        this.manufacturers = new ArrayList<>();
        this.cacheStore = new CacheStore(context, new Session(context));
    }

    @NonNull
    @Override
    public HomeCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_product_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoriesViewHolder holder, int position) {

        final Manufacturer manufacturer = manufacturers.get(position);
        holder.header.setText(manufacturer.getNameAr()
        );
        ProductsAdapter adapter = new ProductsAdapter(manufacturer.getProducts(), cacheStore, null);
        holder.productsRV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.productsRV.setAdapter(adapter);
        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManufacturerActivity.start(view.getContext(), manufacturer);
            }
        });
        holder.seeAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManufacturerActivity.start(view.getContext(), manufacturer);
            }
        });


    }

    @Override
    public int getItemCount() {
        return manufacturers.size();
    }

    public void addAllManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
        notifyDataSetChanged();
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
