package com.socket.dekaneh.fragment.profile;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.socket.dekaneh.R;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardDialogFragment extends DialogFragment {


    @BindView(R.id.viewKonfetti)
    KonfettiView viewKonfetti;

    public RewardDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reward_dialog, container, false);
        ButterKnife.bind(this,v);
        return v ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewKonfetti.build()
                .addColors(ContextCompat.getColor(getActivity(),R.color.congrats_color1),ContextCompat.getColor(getActivity(),R.color.congrats_color2))
                .setDirection(0.0, 359.0)
                .setSpeed(1f,5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(viewKonfetti.getX()+ viewKonfetti.getWidth() / 2, viewKonfetti.getY() + viewKonfetti.getHeight() / 3)
                .burst(1000);

    }
}
