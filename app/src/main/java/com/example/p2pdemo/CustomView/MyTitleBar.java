package com.example.p2pdemo.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.p2pdemo.R;

public class MyTitleBar extends LinearLayout {

    TextView nameTitle;
    ImageView imgTitle;
    public MyTitleBar(Context context) {
        super(context);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.titlebar, this);
        nameTitle=view.findViewById(R.id.title_name);
        imgTitle=view.findViewById(R.id.title_img);

    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitleName(String name){
        nameTitle.setText(name);
    }
    public void setImgTitle(int ImgID){
        imgTitle.setVisibility(VISIBLE);
        imgTitle.setImageResource(ImgID);
    }


}
