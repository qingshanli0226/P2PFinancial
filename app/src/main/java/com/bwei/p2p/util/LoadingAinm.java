package com.bwei.p2p.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bwei.p2p.R;

public class LoadingAinm extends Dialog {
    private static AnimationDrawable animationDrawable;
    private static ImageView img ;
    private static RelativeLayout homeLoading ;
    private static LoadingAinm instance;

    public static LoadingAinm getInstance(Context context) {
        if(instance == null) {
            instance = new LoadingAinm(context);
        }
        return instance;
    }

    public LoadingAinm(@NonNull Context context) {
        super(context);
    }

    public LoadingAinm(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingAinm(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_LEFT_ICON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setDimAmount(0f);
        this.setContentView(R.layout.dialog);

        setCancelable(true);
        setCanceledOnTouchOutside(false);

    }

    public static void showLodingView(View view) {
        img = view.findViewById(R.id.dialog_gif);
        homeLoading = view.findViewById(R.id.home_loading);
        homeLoading.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        img.setBackgroundResource(R.drawable.anim_loading);
        animationDrawable = (AnimationDrawable) img.getBackground();
        img.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });
    }
    public static void hideView(View view, int i) {
        if(view==null||animationDrawable==null)
            return;
        if (i==1){
//            加载错误
            img.setBackgroundResource(R.drawable.ic_error_page);
        }else{
            homeLoading.setVisibility(View.INVISIBLE);
            animationDrawable.stop();
            img.setVisibility(View.INVISIBLE);
            animationDrawable=null;
        }    }
}
