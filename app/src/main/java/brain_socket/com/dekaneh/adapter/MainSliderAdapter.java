package brain_socket.com.dekaneh.adapter;

import android.util.Log;
import android.view.View;

import java.util.List;

import brain_socket.com.dekaneh.activity.product_details.ProductDetailsActivity;
import brain_socket.com.dekaneh.network.model.Product;
import brain_socket.com.dekaneh.network.model.SliderImage;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    private static final String TAG = MainSliderAdapter.class.getSimpleName();
    private List<SliderImage> images;

    public MainSliderAdapter(List<SliderImage> images) {
        this.images = images;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onBindImageSlide(int position, final ImageSlideViewHolder imageSlideViewHolder) {

        final SliderImage image = images.get(position);
        imageSlideViewHolder.bindImageSlide(image.getImage());
        imageSlideViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: seeexsd");
            }
        });

    }

    public List<SliderImage> getImages() {
        return images;
    }
}
