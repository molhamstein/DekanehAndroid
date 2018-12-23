package com.socket.dekaneh.fragment.categories;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.adapter.CategoriesAdapter;
import com.socket.dekaneh.base.BaseFragment;
import com.socket.dekaneh.network.model.Category;
import butterknife.BindView;

public class CategoriesFragment extends BaseFragment implements CategoriesFragmentVP.View {

    @Inject
    CategoriesFragmentVP.Presenter<CategoriesFragmentVP.View> presenter;
    @Inject
    CategoriesAdapter adapter;

    @BindView(R.id.categoriesRV)
    RecyclerView categoriesRV;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void init(View rootView) {

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        presenter.onAttach(this);

        categoriesRV.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categoriesRV.setAdapter(adapter);
    }

    @Override
    public int rootViewLayoutId() {
        return R.layout.fragment_categories;
    }

    @Override
    public String TAG() {
        return CategoriesFragment.class.getSimpleName();
    }

    @Override
    public void addCategories(List<Category> categories) {
        adapter.addAllCategories(categories);
    }
}
