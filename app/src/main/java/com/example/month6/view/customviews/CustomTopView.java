package com.example.month6.view.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.month6.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomTopView extends RelativeLayout {
    @BindView(R.id.topButBack)
    Button topButBack;
    @BindView(R.id.topTitle)
    TextView topTitle;
    @BindView(R.id.topButSet)
    Button topButSet;

    OnTopClickListener titleButListener;

    public void setOnTopClickListener(OnTopClickListener titleButListener) {
        this.titleButListener = titleButListener;
    }

    public CustomTopView(Context context) {
        this(context,null);
    }

    public CustomTopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTopView);
        String title = typedArray.getString(R.styleable.CustomTopView_titleText);
        boolean backShow = typedArray.getBoolean(R.styleable.CustomTopView_backShow,false);
        boolean setShow = typedArray.getBoolean(R.styleable.CustomTopView_setShow,false);

        View view = inflate(context, R.layout.custom_top, this);
        ButterKnife.bind(view);

        topButBack.setVisibility(backShow?VISIBLE:GONE);
        topButSet.setVisibility(setShow?VISIBLE:GONE);
        topTitle.setText(title);
        //返回  设置  点击事件回调
        topButBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleButListener.onBackButClick(v);
            }
        });
        topButSet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleButListener.onSetButClick(v);
            }
        });
    }

}
