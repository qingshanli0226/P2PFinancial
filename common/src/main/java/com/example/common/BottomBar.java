package com.example.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

public class BottomBar extends LinearLayout {

    Context context;
    OnTapListener onTapListener;
    RadioGroup MainGroup;
    RadioButton MainFragment;
    RadioButton InvestFragment;
    RadioButton MyinvestFragment;
    RadioButton MoreFragment;
    RadioButton[] radioButtons;
    View view;

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.layout_bottombar, this);
        MainGroup = view.findViewById(R.id.rg_main);
        MainFragment = view.findViewById(R.id.rb_main);
        InvestFragment = view.findViewById(R.id.rb_invest);
        MyinvestFragment = view.findViewById(R.id.rb_myinvest);
        MoreFragment = view.findViewById(R.id.rb_more);
        radioButtons = new RadioButton[]{MainFragment, InvestFragment, MyinvestFragment, MoreFragment};
        MainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int indexInt = 0;
                if (checkedId == R.id.rb_main) {
                    indexInt = 0;
                } else if (checkedId == R.id.rb_invest) {
                    indexInt = 1;
                } else if (checkedId == R.id.rb_myinvest) {
                    indexInt = 2;
                } else if (checkedId == R.id.rb_more) {
                    indexInt = 3;
                }
                if (onTapListener != null) {
                    onTapListener.tapItemClick(indexInt);
                }
            }
        });
    }

    public interface OnTapListener {
        //监听点击下标
        void tapItemClick(int i);
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }

    //设置默认点击item
    public void setOneCliecked(int num) {
        for (int i = 0; i < radioButtons.length; i++) {
            if (i == num) {
                radioButtons[num - 1].setChecked(true);
            }
        }
    }

    //设置tap的数量 不能超过4个
    public void setTapNum(int num) {
        if (num > 4) {
            Toast.makeText(context, "不能超过四个", Toast.LENGTH_SHORT).show();
        }
        for (int i = 0; i < radioButtons.length; i++) {
            if (i >= num) {
                Log.e("####", i + "");
                radioButtons[i].setVisibility(View.GONE);
            }
        }

    }

    //设置每个tap的文本
    public void setTapText(String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            radioButtons[i].setText(titles[i]);
        }
    }

    //设置每个tap的图标
    public void setTapDrables(@Nullable Drawable[] resId) {
        for (int i = 0; i < resId.length; i++) {
            radioButtons[i].setCompoundDrawablesWithIntrinsicBounds(null, resId[i], null, null);
        }
    }

}
