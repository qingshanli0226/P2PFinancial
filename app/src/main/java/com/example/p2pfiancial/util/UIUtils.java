package com.example.p2pfiancial.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.p2pfiancial.common.MyApplication;

/**
 * 专门提供为处理一些UI相关的问题而创建的工具类，
 * 提供资源获取的通用方法，避免每次都写重复的代码获取结果。
 */
public class UIUtils {
    public static Context getContext(){
        return MyApplication.context;
    }

    public static Handler getHandler(){
        return MyApplication.handler;
    }


    public static void toast(String message, boolean isLengthLong){
        Toast.makeText(UIUtils.getContext(), message, isLengthLong ? Toast.LENGTH_LONG:Toast.LENGTH_SHORT).show();
    }
}
