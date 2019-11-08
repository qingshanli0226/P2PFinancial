package com.example.commen.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;

import com.example.commen.R;

/**
 * 加载页面显示
 */
public class LoadingPage {
    private static LoadingPage instance;
    private Context mContext;

    //常量
    public final static int PAGE_LOADING_CODE = 1001; //正在加载
    public final static int PAGE_ERROR_CODE = 1002; //加载数据失败,
    public final static int PAGE_EMPTY_CODE = 1003; //没有数据
    private AlertDialog.Builder builder;

    //三种布局
    private View loadingView = null;
    private View errorView = null;
    private View emptyView = null;
    private AlertDialog alertDialog;
    private PopupWindow popupWindow;
    private int layoutId;

    private LoadingPage(Context context) {
        this.mContext = context;

        loadingView = LayoutInflater.from(mContext).inflate(R.layout.page_loading, null);
        errorView = LayoutInflater.from(mContext).inflate(R.layout.page_error, null);
        emptyView = LayoutInflater.from(mContext).inflate(R.layout.page_empty, null);
    }

    public static LoadingPage getInstance(Context context) {
        if (instance == null) {
            instance = new LoadingPage(context);
        }
        return instance;
    }

    public @LayoutRes
    int showLoading(int showCode, @LayoutRes int layoutId) {
        this.layoutId = layoutId;
        //显示不同的页面
        if (showCode == PAGE_LOADING_CODE) {//loading页面

            return R.layout.page_loading;
        } else if (showCode == PAGE_ERROR_CODE) { //error页面

            return R.layout.page_error;
        } else if (showCode == PAGE_EMPTY_CODE) {//数据为空界面

            return R.layout.page_empty;
        } else {
            return layoutId;
        }
    }


    public @LayoutRes
    int hideLoading() {

        return layoutId;
    }

//    //显示加载页面
//    public PopupWindow showLoading(int showCode) {
//        hideLoading(); //先隐藏
//        if (popupWindow == null) {
//            popupWindow = new PopupWindow();
//            popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//            popupWindow.setFocusable(true);
//            popupWindow.setOutsideTouchable(false); //设置外部不可触摸
//        }
//
//        //显示不同的页面
//        if (showCode == PAGE_LOADING_CODE) {//loading页面
//
//            if (loadingView != null) {
//                popupWindow.setContentView(loadingView);
//            }
//
//        } else if (showCode == PAGE_ERROR_CODE) { //error页面
//            if (errorView != null) {
//                popupWindow.setContentView(errorView);
//            }
//
//        } else if (showCode == PAGE_EMPTY_CODE) {//数据为空界面
//            if (emptyView != null) {
//                popupWindow.setContentView(emptyView);
//            }
//        }
//
//        return popupWindow;
//    }
//
//    public void hideLoading() {
//        if (popupWindow != null && popupWindow.isShowing()) {
//            popupWindow.dismiss();
//        }
//    }
}
