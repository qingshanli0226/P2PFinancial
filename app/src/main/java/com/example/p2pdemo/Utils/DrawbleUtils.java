package com.example.p2pdemo.Utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawbleUtils {

    @SuppressLint("WrongConstant")
    public static Drawable getDrawble(int rgb, float radius){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setStroke(1,rgb);
        return gradientDrawable;
    }
    public static StateListDrawable getSelector(Drawable normalDrawble,Drawable progressDrawable){
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed},progressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled},normalDrawble);

        stateListDrawable.addState(new int[]{},normalDrawble);
        return stateListDrawable;

    }
}
