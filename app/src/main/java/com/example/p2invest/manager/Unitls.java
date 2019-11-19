package com.example.p2invest.manager;

import android.content.Context;

import com.example.p2invest.MyApplication;

public class Unitls {
    public static Context getContext(){
        return MyApplication.context;
    }
    //将dp转化为px
    public static int dp2px(int dp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);//实现四舍五入
    }
}
