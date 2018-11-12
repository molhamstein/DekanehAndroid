package brain_socket.com.dekaneh.activity.rating;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import javax.inject.Inject;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.Rating;
import brain_socket.com.dekaneh.RatingPagerTranformer;
import brain_socket.com.dekaneh.adapter.RatingViewPagerAdapter;
import brain_socket.com.dekaneh.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingActivity extends BaseActivity implements RatingActivityVP.View {

    @Inject
    RatingActivityVP.Presenter<RatingActivityVP.View> presenter;

    @BindView(R.id.ratingViewPager)
    ViewPager pager;
    RatingViewPagerAdapter adapter;

    public static void start(Context context, String orderId) {
        Intent starter = new Intent(context, RatingActivity.class);
        Intent intent = new Intent();
        intent.putExtra("orderId", orderId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        presenter.onAttach(this);
        adapter = new RatingViewPagerAdapter();
        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new RatingPagerTranformer());
        adapter.setOnRatingTextClickListener(new RatingViewPagerAdapter.OnRatingTextClickListener() {
            @Override
            public void onClick(Rating.Rate rate, int position) {
                pager.setCurrentItem(position);
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.setRate(adapter.getRatingInPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }
}
