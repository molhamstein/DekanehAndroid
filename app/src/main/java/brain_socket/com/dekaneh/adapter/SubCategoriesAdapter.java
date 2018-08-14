package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import brain_socket.com.dekaneh.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoriesViewHolder> {

    private List<String> titles;

    public SubCategoriesAdapter(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public SubCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoriesViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    class SubCategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemSubCatTitle)
        TextView title;

        public SubCategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
