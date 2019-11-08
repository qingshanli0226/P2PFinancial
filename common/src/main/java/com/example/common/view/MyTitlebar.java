package com.example.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common.R;

public class MyTitlebar extends LinearLayout {

    private Context context;

    private TextView tv_title;
    private ImageView iv_title_setting;
    private ImageView iv_title_back;

    private boolean flag_back = false;
    private boolean flag_setting = false;

    private OnClickListener listener;
    private OnTitleClick onTitleClick;


    public MyTitlebar(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public MyTitlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public MyTitlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater from = LayoutInflater.from(context);
        View view = from.inflate(R.layout.common_title, this);

        iv_title_setting = view.findViewById(R.id.iv_title_setting);
        iv_title_back = view.findViewById(R.id.iv_title_back);
        tv_title = view.findViewById(R.id.tv_title);

        if(flag_back){
            iv_title_back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        onTitleClick.OnBackClick();
                    }
                }
            });
        }

        if(flag_setting){
            tv_title.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        onTitleClick.OnSettingClick();
                    }
                }
            });
        }
    }

    public void setOnTitleClick(OnTitleClick onTitleClick){
        this.onTitleClick = onTitleClick;
    }

    public void showSettingImage(){
        iv_title_setting.setVisibility(VISIBLE);
        flag_setting = true;
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public void showBackImage(){
        iv_title_back.setVisibility(VISIBLE);
        flag_back = true;
    }

    public interface OnTitleClick{
        void OnBackClick();
        void OnSettingClick();
    }

}
