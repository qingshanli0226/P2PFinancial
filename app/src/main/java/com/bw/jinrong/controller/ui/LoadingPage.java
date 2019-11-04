package com.bw.jinrong.controller.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class LoadingPage extends FrameLayout {

    //定义四种不同的显示状态
    private static final int STATE_LOADING = 1;
    private static final int STATE_ERROR = 2;
    private static final int STATE_EMPTY = 3;
    private static final int STATE_SUCCESS = 4;

    //默认情况下，当前状态为正在加载
    private int state_current = STATE_LOADING;

    //提供四种不同的界面
    private View view_loading;
    private View view_error;
    private View view_empty;
    private View view_success;
    private LayoutParams params;

    private Context mContext;

    public LoadingPage( Context context) {
        super(context);
    }

    public LoadingPage( Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingPage( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        init();

    }

    //初始化方法
    private void init() {
        //实例化view
        //提供布局显示的参数
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        if (view_loading == null){
            //加载布局
            view_loading = UIUtils
        }
    }
}
