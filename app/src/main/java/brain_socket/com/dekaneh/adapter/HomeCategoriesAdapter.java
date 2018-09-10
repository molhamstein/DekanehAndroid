package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.model.HomeCategory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder> {

    private List<HomeCategory> categories;


    @Inject
    public HomeCategoriesAdapter(List<HomeCategory> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public HomeCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_product_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoriesViewHolder holder, int position) {

        HomeCategory category = categories.get(position);
        holder.header.setText(category.getTitleAr());
        ProductsAdapter adapter = new ProductsAdapter(category.getProducts());
        holder.productsRV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.productsRV.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addCategories(List<HomeCategory> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    class HomeCategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productSectionHeader)
        TextView header;
        @BindView(R.id.mainProductsRV)
        RecyclerView productsRV;

        HomeCategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}