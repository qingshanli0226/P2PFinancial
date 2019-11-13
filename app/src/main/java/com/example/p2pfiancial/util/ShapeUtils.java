package com.example.p2pfiancial.util;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class ShapeUtils {
    public static GradientDrawable getRoundRectDrawable(int radius, int color, boolean isFill, int strokeWidth) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius); //设置角半径
        drawable.setColor(isFill ? color : Color.TRANSPARENT);
        drawable.setStroke(isFill ? 0 : strokeWidth, color);

        return drawable;
    }
}
