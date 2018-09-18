package brain_socket.com.dekaneh.adapter;

import java.util.List;

import brain_socket.com.dekaneh.network.model.SliderImage;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    private List<SliderImage> images;

    public MainSliderAdapter(List<SliderImage> images) {
        this.images = images;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        SliderImage image = images.get(position);
        imageSlideViewHolder.bindImageSlide(image.getImage());

    }

}
