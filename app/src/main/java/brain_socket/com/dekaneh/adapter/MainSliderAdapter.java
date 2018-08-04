package brain_socket.com.dekaneh.adapter;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    private List<Integer> ids;

    public MainSliderAdapter(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        int drawableId = ids.get(position);
        imageSlideViewHolder.bindImageSlide(drawableId);

    }
}
