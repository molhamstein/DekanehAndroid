package com.socket.dekaneh.custom;

import android.view.animation.Interpolator;

public class DekanehInterpolator implements Interpolator {

    private float factor = 0.3f;

    public DekanehInterpolator(float factor) {
        this.factor = factor;
    }

    @Override
    public float getInterpolation(float v) {
        return (float) (Math.pow(2, (-10 * v)) * Math.sin(((2* Math.PI) * (v - (factor/4)))/factor) + 1);
    }
}
