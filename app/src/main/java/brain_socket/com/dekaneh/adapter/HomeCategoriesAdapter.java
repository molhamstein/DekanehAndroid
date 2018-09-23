package brain_socket.com.dekaneh.adapter;

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

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.category_details.CategoryDetailsActivity;
import brain_socket.com.dekaneh.network.CacheStore;
import brain_socket.com.dekaneh.network.Session;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder> {

    private CacheStore cacheStore;
    private List<HomeCategory> categories;


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

    @Override
    public void onBindViewHolder(@NonNull HomeCategoriesViewHolder holder, int position) {

        final HomeCategory category = categories.get(position);
        holder.header.setText(category.getTitleAr());
        ProductsAdapter adapter = new ProductsAdapter(category.getProducts(), cacheStore ,category);
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
