package com.example.month6.view.diyview;

import android.content.Context;
import android.util.AttributeSet;
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

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.top, this);
        ButterKnife.bind(view);
        //返回点击
        topButBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().removeTopActivity();
            }
        });
        topButBack.setVisibility(GONE);
        topButSet.setVisibility(GONE);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
