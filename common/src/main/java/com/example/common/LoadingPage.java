package com.example.common;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class LoadingPage extends LinearLayout {

    //声明两个布局常量
    public static final int LOADING_PAGE = 1;
    public static final int FAILURE_PAGE = 2;
    private Context context;
    private int currentPage;

    private View failureView;

    ImageView loadingImg;
    AnimationDrawable animationDrawable;
    LinearLayout failureImg;
    View loadingView;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    private void init(int currentPage) {
        if (currentPage == LOADING_PAGE) {
            Log.e("####", "加载页面");
            loadingView = LayoutInflater.from(context).inflate(R.layout.layout_loading, this);
            loadingImg = loadingView.findViewById(R.id.iv_allinvest_loading);
            animationDrawable = (AnimationDrawable) loadingImg.getBackground();
            if (!animationDrawable.isRunning()) {
                animationDrawable.start();
            }
        } else if (currentPage == FAILURE_PAGE) {
            Log.e("####", "错误页面");
            failureView = LayoutInflater.from(context).inflate(R.layout.layout_failure, this);
            failureImg = failureView.findViewById(R.id.failure);

        }
        if (addResetListener != null && failureView != null) {
            failureImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    addResetListener.resetLoading();
                }
            });
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public addResetListener addResetListener;

    public void setAddResetListener(LoadingPage.addResetListener addResetListener) {
        this.addResetListener = addResetListener;
    }

    public interface addResetListener {
        public void resetLoading();
    }

    //根据传进来的值判断页面
    public void startLoading(int currentPage) {
        this.currentPage = currentPage;
        Log.e("####", "" + currentPage);
        removeAllViews();
        init(currentPage);
    }

    //加载成功
    public void isSucceed() {
        if (animationDrawable != null) {
            if (animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
            this.setVisibility(GONE);
        }
    }
}
