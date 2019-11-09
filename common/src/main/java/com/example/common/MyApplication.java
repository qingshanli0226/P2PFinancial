package com.example.common;

import android.app.Application;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class MyApplication extends Application {

    public static MyApplication myApplication;
    private RotateAnimation rotateAnimation;

    public static  MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setRotateAnimation();
        myApplication = this;
    }

    private void setRotateAnimation() {
        rotateAnimation = new RotateAnimation(0,359, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
    }

    public RotateAnimation getRotateAnimaiton(){
        return rotateAnimation;
    }
}
