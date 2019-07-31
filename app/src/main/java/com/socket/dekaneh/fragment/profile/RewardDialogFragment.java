package com.socket.dekaneh.fragment.profile;


import android.content.Intent;
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

import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.socket.dekaneh.R;
import com.socket.dekaneh.activity.main.MainActivity;
import com.socket.dekaneh.network.model.Award;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardDialogFragment extends DialogFragment {


    @BindView(R.id.viewKonfetti)
    KonfettiView viewKonfetti;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.body)
    TextView body;

    private Award award ;

    public RewardDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reward_dialog, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewKonfetti.build()
                .addColors(ContextCompat.getColor(getActivity(), R.color.congrats_color1), ContextCompat.getColor(getActivity(), R.color.congrats_color2))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 20f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(view.getX() + view.getWidth() / 2, view.getY())
//                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 50000000000000000L);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("showProfile",true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        award = (Award)getArguments().getSerializable("award");
        title.setText(Locale.getDefault().getLanguage() == "ar"?award.getNameAr():award.getNameEn());
        body.setText(award.getDetails());


    }
}
