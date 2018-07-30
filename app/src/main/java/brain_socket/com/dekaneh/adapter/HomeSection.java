package brain_socket.com.dekaneh.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import brain_socket.com.dekaneh.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class HomeSection extends StatelessSection {

    public HomeSection() {
        super(SectionParameters.builder()
        .itemResourceId(R.layout.item_product_home)
        .headerResourceId(R.layout.item_items_header)
        .build());
    }

    @Override
    public int getContentItemsTotal() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new ItemHeaderViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        ItemViewHolder(View itemView) {
            super(itemView);
        }

    }

    class ItemHeaderViewHolder extends RecyclerView.ViewHolder {

        ItemHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
