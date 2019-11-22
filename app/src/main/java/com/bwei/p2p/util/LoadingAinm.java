package com.bwei.p2p.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.p2p.R;

public class LoadingAinm extends ProgressDialog {
    private static AnimationDrawable animationDrawable;
    public static ImageView img ;
    private static int mAnimation;
    private static TextView dialogText;
    private static String message;

    public LoadingAinm(Context context, String message,int id) {
        super(context);
        setCanceledOnTouchOutside(true);
        this.mAnimation=id;
        this.message=message;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        img.setBackgroundResource(mAnimation);
        if (message.equals("网络出差了")){
            dialogText.setText(R.string.netout);
        }else{
        animationDrawable = (AnimationDrawable) img.getBackground();
            img.post(new Runnable() {
                @Override
                public void run() {
                    animationDrawable.start();
                }
            });
            dialogText.setText(R.string.onloading);
        }
    }

    private void initView() {
        setContentView(R.layout.dialog);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width=WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height=WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
        img = findViewById(R.id.dialog_gif);
        dialogText = findViewById(R.id.anim_tv);
    }
    //    private static RelativeLayout homeLoading ;
//    private static LoadingAinm instance;
//    Context context;
//    public LoadingAinm(Context context) {
//        this.context=context;
//    }
//
//    public static LoadingAinm getInstance(Context context) {
//        if(instance == null) {
//            instance = new LoadingAinm(context);
//        }
//        return instance;
//    }
//    public static void showLodingView(View view) {
//        img = view.findViewById(R.id.dialog_gif);
//        homeLoading = view.findViewById(R.id.home_loading);
//        homeLoading.setVisibility(View.VISIBLE);
//        img.setVisibility(View.VISIBLE);
//        img.setBackgroundResource(R.drawable.anim_loading);
//        animationDrawable = (AnimationDrawable) img.getBackground();
//
//        img.post(new Runnable() {
//            @Override
//            public void run() {
//                animationDrawable.start();
//            }
//        });
//    }
//    public static void hideView(View view, int i) {
////        if(view==null||animationDrawable==null)
////            return;
//        if (i==1){
////            加载错误
//            img.setBackgroundResource(R.drawable.ic_error_page);
//        }else{
//            homeLoading.setVisibility(View.INVISIBLE);
//            animationDrawable.stop();
//            img.setVisibility(View.INVISIBLE);
//            animationDrawable=null;
//
//
//        }    }




}
