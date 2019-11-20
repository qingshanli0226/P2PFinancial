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

    private TextView nameTitle;
    private ImageView imgRight,imgLeft;
    private ITitleListener listener;
    public MyTitleBar(Context context) {
        super(context);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.titlebar, this);
        nameTitle=view.findViewById(R.id.title_name);
        imgLeft=view.findViewById(R.id.title_imgLeft);
        imgRight=view.findViewById(R.id.title_imgRight);

    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(){
        imgLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
             listener.leftClick();

                }
            }
        });
        imgRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){

                  listener.rightClick();
                }
            }
        });
    }
    public void setTitlelistener(ITitleListener titlelistener){
        this.listener=titlelistener;
    }


    public void setTitleName(String name){
        nameTitle.setText(name);
    }
    public void setImgRightShow(int ImgID){
        imgRight.setVisibility(VISIBLE);
        imgRight.setImageResource(ImgID);
    }
    public void setImgLeftShow(int img){
        imgLeft.setVisibility(VISIBLE);
        imgLeft.setImageResource(img);
    }

    public interface ITitleListener{
        void leftClick();
        void rightClick();
    }



}
