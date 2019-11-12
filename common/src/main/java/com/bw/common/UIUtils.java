package com.bw.common;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

/**
 * 专门提供为处理一些UI相关的问题而创建的工具类
 * 提供资源获取的通用方法，避免每次都写重复代码获取结果
 */
public class UIUtils {

    private static Context context;
    private static Handler handler;
    private static Thread mainThread;
    private static int mainThreadId;

    public UIUtils() {
    }

    public UIUtils(Context context, Handler handler, Thread mainThread, int mainThreadId) {
        this.context = context;
        this.handler = handler;
        this.mainThread = mainThread;
        this.mainThreadId = mainThreadId;
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    //返回指定colorId对应的颜色值
    public static int getColor(int colorId){
        return getContext().getResources().getColor(colorId);
    }

    //加载指定viewId的视图对象，并返回
    public static View getView(int viewId){
        View view = View.inflate(getContext(),viewId,null);
        return view;
    }

    public static String[] getStringArr(int strArrId){
        String[] startArray = getContext().getResources().getStringArray(strArrId);
        return startArray;
    }

    //将dp转化为px
    public static int dp2px(int dp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        //实现四舍五入
        return (int) (dp * density + 0.5);
    }

    //将px转化为dp
    public static int px2dp(int px){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        //实现四舍五入
        return (int) (px / density + 0.5);
    }

    //保证runnable中的操作在主线程中执行
    public static void runOnUiThread(Runnable runnable){
        if (isInMainThread()){
            runnable.run();
        }else {
            UIUtils.getHandler().post(runnable);
        }
    }

    //判断当前线程是否是主线程
    private static boolean isInMainThread() {
        int currentThreadId = android.os.Process.myTid();
        return mainThreadId == currentThreadId;
    }

    public static void toast(String message,boolean isLengthLong){
        Toast.makeText(context, message, isLengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }


}
