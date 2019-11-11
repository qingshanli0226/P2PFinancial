package com.example.commen.util;

import android.app.Activity;
import android.view.ViewGroup;

import crazyjone.loadinglibrary.View.LoadingStateWidget;
import crazyjone.loadinglibrary.View.StatuWidgetSetting;

/**
 * 加载页面显示
 */
public class LoadingPage {
    private static LoadingPage instance = null;
    private static LoadingStateWidget stateWidget;

    public final static int PAGE_LOADING_CODE = 1001; //正在加载
    public final static int PAGE_ERROR_CODE = 1002; //加载数据失败,
    public final static int PAGE_EMPTY_CODE = 1003; //没有数据
    private StatuWidgetSetting setting;


    public static LoadingPage getInstance() {
        if (instance == null) {
            instance = new LoadingPage();
            stateWidget = new LoadingStateWidget();
        }
        return instance;
    }


    public LoadingPage setActivityAttach(Activity context){
        if (stateWidget != null){
            stateWidget.Attach(context);
            setListener();
        }
        return this;
    }

    public LoadingPage setViewAttach(ViewGroup contain){
        if (stateWidget != null){
            stateWidget.Attach(contain);
            setListener();
        }
        return this;
    }


    private void setListener(){
        stateWidget.setRetryListener(new LoadingStateWidget.RetryListener() {
            @Override
            public void onRetry() {

            }
        });
    }

    public void show(int showCode){
        if (stateWidget == null){
            return;
        }



        //显示不同的页面
        if (showCode == PAGE_LOADING_CODE) {//loading页面
//            //自定义加载页面
//            setting = stateWidget.getSetting();
//            if (setting == null){
//                setting = new StatuWidgetSetting();
//            }
//            StatuWidgetSetting.LoadingSetting loadingSetting = new StatuWidgetSetting.LoadingSetting(Color.WHITE, R.drawable.wc_ac_05, "休息一下", ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            setting.setLoadingSetting(loadingSetting);
//            stateWidget.setSetting(setting);

            stateWidget.loadingState();
        } else if (showCode == PAGE_ERROR_CODE) { //error页面
            stateWidget.networkState();
        } else if (showCode == PAGE_EMPTY_CODE) {//数据为空界面

            stateWidget.emptyState();
        }
    }

    public void hideLoading(){
        if (stateWidget == null){
            return;
        }
        stateWidget.normalState();
    }

}









































