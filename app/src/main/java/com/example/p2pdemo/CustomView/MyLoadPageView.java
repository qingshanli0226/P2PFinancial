package com.example.p2pdemo.CustomView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.p2pdemo.R;

public class MyLoadPageView extends RelativeLayout {

    RelativeLayout b_loadRel;
    ImageView b_Img;
    TextView t_text;
    public MyLoadPageView(Context context) {
        super(context);
    }

    public MyLoadPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.myload_page, this);
        b_loadRel = view.findViewById(R.id.load_rel);
        b_Img=view.findViewById(R.id.myLoadImg);
        t_text=view.findViewById(R.id.star_text);

    }
    public void showLoad(){
        b_loadRel.setVisibility(VISIBLE);
        b_Img.setVisibility(VISIBLE);
        t_text.setVisibility(VISIBLE);
        AnimationDrawable background = (AnimationDrawable) b_Img.getBackground();
        if(!background.isRunning()){
            background.start();
        }
    }
    public void unShowLoad(){

//        b_Img.setBackgroundDrawable(null);
        AnimationDrawable background = (AnimationDrawable) b_Img.getBackground();
        background.stop();
        b_loadRel.setVisibility(GONE);
//        b_Img.setVisibility(GONE);
//        t_text.setVisibility(GONE);
//        Log.e("##","55555");
    }

    public MyLoadPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
