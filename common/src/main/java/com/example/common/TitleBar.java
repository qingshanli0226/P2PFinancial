package com.example.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class TitleBar extends RelativeLayout {

    //声明
    Context context;
    RelativeLayout relativeLayout;
    TextView mLeftImg;
    TextView mCenterText;
    TextView mRightImg;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        //标题栏布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        //初始化控件
        relativeLayout = view.findViewById(R.id.rl_titlebar);
        mLeftImg = view.findViewById(R.id.left_img);
        mRightImg = view.findViewById(R.id.right_img);
        mCenterText = view.findViewById(R.id.center_title);

        mLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleInterface.leftClick();
            }
        });

        mRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleInterface.rightClick();
            }
        });
    }

    //设置中间标题文本
    public void setTitleText(String text) {
        mCenterText.setText(text);
    }

    //设置左边图标
    public void setLeftImg(@DrawableRes int imgDrawable) {
        mLeftImg.setBackgroundResource(imgDrawable);
    }

    public void setLeftText(String leftText) {
        mLeftImg.setText(leftText);
    }

    public void setLeftTextColor(@ColorInt int leftTextColor) {
        mLeftImg.setTextColor(leftTextColor);
    }

    public void setRightTextColor(@ColorInt int rightTextColor) {
        mRightImg.setTextColor(rightTextColor);
    }

    //设置右边图标
    public void setRightImg(@DrawableRes int imgDrawable) {
        mRightImg.setBackgroundResource(imgDrawable);
    }

    public void setRightText(String rightText) {
        mRightImg.setText(rightText);
    }

    //设置标题栏颜色
    public void setBackGround(@ColorInt int colorRes) {
        relativeLayout.setBackgroundColor(colorRes);
    }

    TitleInterface titleInterface;

    public void setTitleInterface(TitleInterface titleInterface) {
        this.titleInterface = titleInterface;
    }

    public interface TitleInterface {
        void leftClick();

        void rightClick();
    }
}
