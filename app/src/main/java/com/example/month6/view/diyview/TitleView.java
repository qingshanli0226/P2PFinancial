package com.example.month6.view.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.common.diyviews.singleclass.ActivityManager;
import com.example.month6.R;

import butterknife.BindView;

public class TitleView extends RelativeLayout {
    Button topButBack;
    TextView topTitle;
    Button topButSet;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view =inflate(context,R.layout.top,this);
        topButBack=view.findViewById(R.id.topButBack);
        topTitle=view.findViewById(R.id.topTitle);
        topButSet=view.findViewById(R.id.topButSet);

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
