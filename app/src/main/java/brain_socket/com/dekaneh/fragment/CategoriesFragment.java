package brain_socket.com.dekaneh.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.adapter.CategoriesAdapter;
import brain_socket.com.dekaneh.base.BaseFragment;
import butterknife.BindView;

public class CategoriesFragment extends BaseFragment {

    @BindView(R.id.categoriesRV)
    RecyclerView categoriesRV;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void init(View rootView) {

        categoriesRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<String> titles = new ArrayList<>();
        titles.add("غذائيات");
        titles.add("رواق");
        titles.add("دخان");
        titles.add("غذائيات");
        titles.add("رواق");
        titles.add("دخان");
        titles.add("غذائيات");
        titles.add("رواق");
        titles.add("دخان");
        categoriesRV.setAdapter(new CategoriesAdapter(titles));
    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_categories;
    }

    @Override
    public String TAG() {
        return CategoriesFragment.class.getSimpleName();
    }
}
