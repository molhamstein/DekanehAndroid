package brain_socket.com.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.Rating;

public class RatingViewPagerAdapter extends PagerAdapter {

    private List<Rate> rates;
    private OnRatingTextClickListener listener;

    public RatingViewPagerAdapter() {
        rates = new ArrayList<>();
        rates.add(Rate.GREAT);
        rates.add(Rate.OK);
        rates.add(Rate.BAD);
    }

    @Override
    public int getCount() {
        return rates.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_rating, container, false);
        View background = view.findViewById(R.id.ratingBackground);
        ImageView face = view.findViewById(R.id.ratingFace);
        View greatText = view.findViewById(R.id.greatText);
        View okText = view.findViewById(R.id.okText);
        View badText = view.findViewById(R.id.badText);
        switch (rates.get(position)) {
            case GREAT:
                greatText.setAlpha(1f);
                okText.setAlpha(0.4f);
                badText.setAlpha(0.4f);
                background.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.great_rating_color));
                Picasso.get().load(R.drawable.great_face).into(face);
                break;
            case OK:
                greatText.setAlpha(0.4f);
                okText.setAlpha(1f);
                badText.setAlpha(0.4f);
                background.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.ok_rating_color));
                Picasso.get().load(R.drawable.ok_face).into(face);
                break;
            case BAD:
                greatText.setAlpha(0.4f);
                okText.setAlpha(0.4f);
                badText.setAlpha(1f);
                background.setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.bad_rating_color));
                Picasso.get().load(R.drawable.bad_face).into(face);
                break;
        }


        greatText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(Rating.Rate.happy, 0);
            }
        });

        okText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(Rating.Rate.normal, 1);
            }
        });

        badText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(Rating.Rate.sad, 2);
            }
        });



        container.addView(view);

        return view;
    }

    public Rating.Rate getRatingInPosition(int position) {
        switch (position) {
            case 0:
                return Rating.Rate.happy;
            case 1:
                return Rating.Rate.normal;
            case 2:
                return Rating.Rate.sad;
        }
        return Rating.Rate.happy;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    public void setOnRatingTextClickListener(OnRatingTextClickListener listener) {
        this.listener = listener;
    }

    public enum Rate {
        GREAT, OK, BAD
    }

    public interface OnRatingTextClickListener {
        void onClick(Rating.Rate rate, int position);
    }
}
