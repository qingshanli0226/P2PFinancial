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
    RadioGroup rg_main;
    RadioButton rb_main;
    RadioButton rb_invest;
    RadioButton rb_myinvest;
    RadioButton rb_more;
    RadioButton[] radioButtons;
    View inflate;
    @LayoutRes
    int resLayout;

    public BottomBar(Context context) {
        super(context);
        init();
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        inflate = layoutInflater.inflate(R.layout.layout_bottombar, this);
        rg_main = inflate.findViewById(R.id.rg_main);
        rb_main = inflate.findViewById(R.id.rb_main);
        rb_invest = inflate.findViewById(R.id.rb_invest);
        rb_myinvest = inflate.findViewById(R.id.rb_myinvest);
        rb_more = inflate.findViewById(R.id.rb_more);
        radioButtons = new RadioButton[]{rb_main, rb_invest, rb_myinvest, rb_more};
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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


    public void setResLayout(int resLayout) {
        this.resLayout = resLayout;
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnTapListener {
        void tapItemClick(int i);
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }

    public void setOneCliecked(int num) {
        for (int i = 0; i < radioButtons.length; i++) {
            if (i == num) {
                radioButtons[num - 1].setChecked(true);
            }
        }
    }

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

    public void setTapText(String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            radioButtons[i].setText(titles[i]);
        }
    }

    public void setTapDrables(@Nullable Drawable[] resId) {
        for (int i = 0; i < resId.length; i++) {
            radioButtons[i].setCompoundDrawablesWithIntrinsicBounds(null, resId[i], null, null);
        }
    }

}
