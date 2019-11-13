package com.example.p2pfiancial.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.p2pfiancial.app.MyApplication;

/**
 * 专门提供为处理一些UI相关的问题而创建的工具类，
 * 提供资源获取的通用方法，避免每次都写重复的代码获取结果。
 */
public class UIUtils {
    private static Fragment currentFragment;

    public static Context getContext() {
        return MyApplication.context;
    }

    public static Handler getHandler() {
        return MyApplication.handler;
    }


    public static void toast(String message, boolean isLengthLong) {
        Toast.makeText(UIUtils.getContext(), message, isLengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static void switchFragment(Context context, int viewId, Fragment targetFragment) {
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(viewId, targetFragment, targetFragment.getClass().getName()).commit();
        } else {
            transaction.hide(currentFragment).show(targetFragment).commit();
        }

        currentFragment = targetFragment;
    }

    //将dp转化为px
    public static int dp2px(int dp) {
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5); //实现四舍五入
    }

    //将px转化为dp
    public static int px2dp(int px) {
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5); //实现四舍五入
    }
}
