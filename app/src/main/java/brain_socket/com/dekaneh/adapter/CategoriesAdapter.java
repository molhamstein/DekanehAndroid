package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.CategoryDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<String> titles;

    public CategoriesAdapter(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        holder.title.setText(titles.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDetailsActivity.start(view.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_title)
        TextView title;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
