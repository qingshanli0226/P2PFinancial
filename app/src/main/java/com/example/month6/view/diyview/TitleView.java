package com.example.month6.view.diyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.common.diyviews.singleclass.ActivityManager;
import com.example.month6.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleView extends RelativeLayout {
    @BindView(R.id.topButBack)
    Button topButBack;
    @BindView(R.id.topTitle)
    TextView topTitle;
    @BindView(R.id.topButSet)
    Button topButSet;

    TitleButListener titleButListener;

    public void setTitleButListener(TitleButListener titleButListener) {
        this.titleButListener = titleButListener;
    }

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        String title = typedArray.getString(R.styleable.TitleView_titleText);
        boolean backShow = typedArray.getBoolean(R.styleable.TitleView_backShow,false);
        boolean setShow = typedArray.getBoolean(R.styleable.TitleView_setShow,false);

        View view = inflate(context, R.layout.top, this);
        ButterKnife.bind(view);

        topButBack.setVisibility(backShow?VISIBLE:GONE);
        topButSet.setVisibility(setShow?VISIBLE:GONE);
        topTitle.setText(title);
        //返回  设置  点击事件回调
        topButBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleButListener.backButClick(v);
            }
        });
        topButSet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleButListener.setButClick(v);
            }
        });
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
