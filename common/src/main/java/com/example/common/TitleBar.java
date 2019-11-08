package com.example.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class TitleBar extends RelativeLayout {

    Context context;

    public TitleBar(Context context) {
        super(context);


    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    RelativeLayout relativeLayout;
    ImageView imageView;
    TextView textView;

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        relativeLayout = view.findViewById(R.id.rl_titlebar);
        imageView = view.findViewById(R.id.left_img);
        textView = view.findViewById(R.id.center_title);
    }

    public void setTitleText(String text) {
        textView.setText(text);
    }

    public void setLeftImg(Drawable imgDrawable){
        imageView.setImageDrawable(imgDrawable);
    }

    public void setBackGround(@ColorInt int colorRes){
        relativeLayout.setBackgroundColor(colorRes);
    }
}
