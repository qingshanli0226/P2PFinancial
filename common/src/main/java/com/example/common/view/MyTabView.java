package com.example.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.common.R;

public class MyTabView extends LinearLayout {

    private Context context;

    private RadioGroup rg_group;
    private RadioButton rb_all;
    private RadioButton rb_recommend;
    private RadioButton rb_hot;
    private OnTabClickListener onTabClickListener;

    public MyTabView(Context context) {
        super(context);

        this.context = context;

        initView();

    }

    public MyTabView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        initView();
    }

    public MyTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        initView();
    }

    private void initView() {
        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R.layout.layout_tab,this);
        rg_group = findViewById(R.id.rg_group);
        rb_all = findViewById(R.id.rb_all);
        rb_recommend = findViewById(R.id.rb_recommend);
        rb_hot = findViewById(R.id.rb_hot);

        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb_all){
                    onTabClickListener.OnTabClickChanged(0);
                    setAll();
                }else if(i==R.id.rb_recommend){
                    onTabClickListener.OnTabClickChanged(1);
                    setRecommend();
                }else{
                    onTabClickListener.OnTabClickChanged(2);
                    setHot();
                }
            }
        });
    }

    private void setAll() {
        setColorBack();

        rb_all.setBackgroundColor(getResources().getColor(R.color.my_title_bg));
        rb_all.setTextColor(getResources().getColor(R.color.product_red_common));
    }

    private void setHot() {
        setColorBack();

        rb_hot.setBackgroundColor(getResources().getColor(R.color.my_title_bg));
        rb_hot.setTextColor(getResources().getColor(R.color.product_red_common));
    }

    private void setRecommend() {
        setColorBack();

        rb_recommend.setBackgroundColor(getResources().getColor(R.color.my_title_bg));
        rb_recommend.setTextColor(getResources().getColor(R.color.product_red_common));
    }

    public void setTab(int item){
        switch (item){
            case 0:
                setAll();
                break;
            case 1:
                setRecommend();
                break;
            case 2:
                setHot();
                break;
        }
    }

    private void setColorBack() {
        rb_all.setBackgroundColor(getResources().getColor(R.color.white));
        rb_all.setTextColor(getResources().getColor(R.color.product_detail_common));
        rb_recommend.setBackgroundColor(getResources().getColor(R.color.white));
        rb_recommend.setTextColor(getResources().getColor(R.color.product_detail_common));
        rb_hot.setBackgroundColor(getResources().getColor(R.color.white));
        rb_hot.setTextColor(getResources().getColor(R.color.product_detail_common));
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener){
        this.onTabClickListener = onTabClickListener;
    }

    public interface OnTabClickListener{
        void OnTabClickChanged(int position);
    }
}
