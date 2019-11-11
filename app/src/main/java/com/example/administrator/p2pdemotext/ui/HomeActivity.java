package com.example.administrator.p2pdemotext.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Adapter.Vpadp;
import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.Fragment.FragmentHomepage;
import com.example.administrator.p2pdemotext.R;

import java.util.ArrayList;

import crazyjone.loadinglibrary.View.LoadingStateWidget;

public class HomeActivity extends BaseActivity {
    private TextView HomeActivityTittleBarId;
    private ViewPager HomeActivityViewPager;
    private RadioGroup HomeActivityRadioGroup;
    private RadioButton HomeActivityRadioHome;
    private RadioButton HomeActivityRadioInvestor;
    private RadioButton HomeActivityRadioMyAssets;
    private RadioButton HomeActivityRadioMore;

    ArrayList<Fragment> arr=new ArrayList<>();


    @Override
    protected void initData() {
        //按钮判断
        ButtonLogic();

        //加载页实例化
        LoadingStateWidget load=new LoadingStateWidget();
        load.Attach(this);

        FragmentHomepage f1=new FragmentHomepage();
        arr.add(f1);
        Vpadp adp=new Vpadp(getSupportFragmentManager(),arr,this);
        HomeActivityViewPager.setAdapter(adp);

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
        HomeActivityTittleBarId = (TextView) findViewById(R.id.homeActivityTittleBarId);
        HomeActivityViewPager = (ViewPager) findViewById(R.id.homeActivityViewPager);
        HomeActivityRadioGroup = (RadioGroup) findViewById(R.id.homeActivityRadioGroup);
        HomeActivityRadioHome = (RadioButton) findViewById(R.id.homeActivityRadioHome);
        HomeActivityRadioInvestor = (RadioButton) findViewById(R.id.homeActivityRadioInvestor);
        HomeActivityRadioMyAssets = (RadioButton) findViewById(R.id.homeActivityRadioMyAssets);
        HomeActivityRadioMore = (RadioButton) findViewById(R.id.homeActivityRadioMore);

    }
}
