package com.example.administrator.p2pdemotext.ui;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.R;

public class HomeActivity extends BaseActivity {
    private TextView HomeActivityTittleBarId;
    private ViewPager HomeActivityViewPager;
    private RadioGroup HomeActivityRadioGroup;
    private RadioButton HomeActivityRadioHome;
    private RadioButton HomeActivityRadioInvestor;
    private RadioButton HomeActivityRadioMyAssets;
    private RadioButton HomeActivityRadioMore;




    @Override
    protected void initData() {
        //按钮判断
        ButtonLogic();
    }

    @Override
    protected void initView() {
        init();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    private void ButtonLogic() {
        //首页按钮
        HomeActivityRadioHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivityRadioHome.setTextColor(Color.BLUE);
                HomeActivityRadioInvestor.setTextColor(Color.BLACK);
                HomeActivityRadioMyAssets.setTextColor(Color.BLACK);
                HomeActivityRadioMore.setTextColor(Color.BLACK);
            }
        });
        //投资按钮
        HomeActivityRadioInvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivityRadioHome.setTextColor(Color.BLACK);
                HomeActivityRadioInvestor.setTextColor(Color.BLUE);
                HomeActivityRadioMyAssets.setTextColor(Color.BLACK);
                HomeActivityRadioMore.setTextColor(Color.BLACK);
            }
        });
        //我的资产按钮
        HomeActivityRadioMyAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivityRadioHome.setTextColor(Color.BLACK);
                HomeActivityRadioInvestor.setTextColor(Color.BLACK);
                HomeActivityRadioMyAssets.setTextColor(Color.BLUE);
                HomeActivityRadioMore.setTextColor(Color.BLACK);
            }
        });
        //更多按钮
        HomeActivityRadioMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivityRadioHome.setTextColor(Color.BLACK);
                HomeActivityRadioInvestor.setTextColor(Color.BLACK);
                HomeActivityRadioMyAssets.setTextColor(Color.BLACK);
                HomeActivityRadioMore.setTextColor(Color.BLUE);
            }
        });
    }

    private void init() {
        HomeActivityTittleBarId = (TextView) findViewById(R.id.HomeActivityTittleBarId);
        HomeActivityViewPager = (ViewPager) findViewById(R.id.HomeActivityViewPager);
        HomeActivityRadioGroup = (RadioGroup) findViewById(R.id.HomeActivityRadioGroup);
        HomeActivityRadioHome = (RadioButton) findViewById(R.id.HomeActivityRadioHome);
        HomeActivityRadioInvestor = (RadioButton) findViewById(R.id.HomeActivityRadioInvestor);
        HomeActivityRadioMyAssets = (RadioButton) findViewById(R.id.HomeActivityRadioMyAssets);
        HomeActivityRadioMore = (RadioButton) findViewById(R.id.HomeActivityRadioMore);

    }
}
