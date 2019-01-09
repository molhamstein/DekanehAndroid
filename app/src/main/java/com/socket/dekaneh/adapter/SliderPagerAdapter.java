package com.socket.dekaneh.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.socket.dekaneh.activity.manufacturer.ManufacturerActivity;
import com.socket.dekaneh.network.model.Manufacturer;
import com.socket.dekaneh.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.product_details.ProductDetailsActivity;
import com.socket.dekaneh.network.model.Product;
import com.socket.dekaneh.network.model.SliderImage;

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
        Picasso.get().load(sliderImage.getImage()).into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sliderImage.getType().equals("product")) {
                    Product product = new Product(sliderImage.getTarget());
                    ProductDetailsActivity.start(container.getContext(), product);
                }

                if (sliderImage.getType().equals("manufacturer")) {
                    ManufacturerActivity.start(view.getContext(), new Manufacturer(sliderImage.getTarget()));
                }

                if (sliderImage.getType().equals("external")) {
                    ViewUtils.openUrl(view.getContext(), sliderImage.getTarget());
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
