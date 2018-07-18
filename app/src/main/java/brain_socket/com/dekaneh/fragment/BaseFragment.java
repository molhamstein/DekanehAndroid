package brain_socket.com.dekaneh.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brain_socket.com.dekaneh.activity.registration.FragmentNavigationVP;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements FragmentNavigationVP.View {

    protected FragmentNavigationVP.Presenter navigationPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(rootViewLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        init(rootView);
        return rootView;
    }

    public abstract void init(View rootView);

    /**
     * e.g
     * @return R.id.layout_name
     **/
    public abstract int rootViewLayoutId();
    public abstract String TAG();

    @Override
    public void attachPresenter(FragmentNavigationVP.Presenter presenter) {
        this.navigationPresenter = presenter;
    }


}