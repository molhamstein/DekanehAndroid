package com.socket.dekaneh.activity.rating;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import com.socket.dekaneh.R;
import com.socket.dekaneh.Rating;
import com.socket.dekaneh.RatingPagerTranformer;
import com.socket.dekaneh.activity.main.MainActivity;
import com.socket.dekaneh.adapter.RatingViewPagerAdapter;
import com.socket.dekaneh.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RatingActivity extends BaseActivity implements RatingActivityVP.View {

    @Inject
    RatingActivityVP.Presenter<RatingActivityVP.View> presenter;

    @BindView(R.id.ratingViewPager)
    ViewPager pager;
    RatingViewPagerAdapter adapter;

    public static void start(Context context, String orderId) {
        Intent starter = new Intent(context, RatingActivity.class);
        starter.putExtra("orderId", orderId);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

    @OnClick(R.id.sendRatingBtn)
    public void onSubmitRatingBtnClick() {
        presenter.submitRate();
    }

    @OnClick(R.id.noHelpBtn)
    public void onNoHelpBtnClick() {
        finish();
    }

    @Override
    public void finish() {
        MainActivity.start(this);
        super.finish();
    }
}
