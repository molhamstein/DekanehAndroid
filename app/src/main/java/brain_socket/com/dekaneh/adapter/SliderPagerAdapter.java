package brain_socket.com.dekaneh.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import brain_socket.com.dekaneh.R;
import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.SliderImage;

import static java.security.AccessController.getContext;

public class SliderPagerAdapter extends PagerAdapter {

    private List<SliderImage> images;

    public SliderPagerAdapter(List<SliderImage> images) {
        this.images = images;
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide, container, false);
        ImageView image = view.findViewById(R.id.sliderImage);
        final SliderImage sliderImage = images.get(position);
        Log.d("ASDADS", "instantiateItem: " + sliderImage.getImage());
        Picasso.get().load(sliderImage.getImage()).into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sliderImage.getType().equals("product")) {
                    Product product = new Product(sliderImage.getTarget());
                    ProductDetailsActivity.start(container.getContext(), product);
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

}
