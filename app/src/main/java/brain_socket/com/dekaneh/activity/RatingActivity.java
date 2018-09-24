package brain_socket.com.dekaneh.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.RatingPagerTranformer;
import brain_socket.com.dekaneh.adapter.RatingViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingActivity extends AppCompatActivity {

    @BindView(R.id.ratingViewPager)
    ViewPager pager;
    RatingViewPagerAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, RatingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);
        adapter = new RatingViewPagerAdapter();
        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new RatingPagerTranformer());
        adapter.setOnRatingTextClickListener(new RatingViewPagerAdapter.OnRatingTextClickListener() {
            @Override
            public void onClick(int position) {
                pager.setCurrentItem(position);
            }
        });
    }
}
