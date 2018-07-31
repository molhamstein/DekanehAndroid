package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.network.model.ProductsSection;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionedProductsAdapter extends RecyclerView.Adapter<SectionedProductsAdapter.ProductsViewHolder> {

    private List<ProductsSection> productsSections;


    public SectionedProductsAdapter(List<ProductsSection> productsSections) {
        this.productsSections = productsSections;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_product_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        ProductsSection section = productsSections.get(position);
        holder.header.setText(section.getHeader());
        ProductsAdapter adapter = new ProductsAdapter(section.getProducts());
        holder.productsRV.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.productsRV.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return productsSections.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productSectionHeader)
        TextView header;
        @BindView(R.id.mainProductsRV)
        RecyclerView productsRV;

        ProductsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
