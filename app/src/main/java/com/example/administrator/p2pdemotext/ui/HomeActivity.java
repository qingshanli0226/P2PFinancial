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
import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Fragment.FragmentHomepage;
import com.example.administrator.p2pdemotext.Fragment.FragmentInvest;
import com.example.administrator.p2pdemotext.Fragment.FragmentMore;
import com.example.administrator.p2pdemotext.Fragment.FragmentMyAssets;
import com.example.administrator.p2pdemotext.R;


import java.util.ArrayList;
import java.util.List;

import crazyjone.loadinglibrary.View.LoadingStateWidget;

public class HomeActivity extends BaseActivity<Bean> {
    private TextView homeActivityTittleBarId;
    private ViewPager homeActivityViewPager;
    private RadioGroup homeActivityRadioGroup;
    private RadioButton homeActivityRadioHome;
    private RadioButton homeActivityRadioInvestor;
    private RadioButton homeActivityRadioMyAssets;
    private RadioButton homeActivityRadioMore;

    ArrayList<Fragment> arr=new ArrayList<>();


    @Override
    protected void initData() {
        //Viewpager滑动判断
        ViewPagerJudge();


        //按钮判断
        ButtonLogic();

        //加载页实例化
        LoadingStateWidget load=new LoadingStateWidget();
        load.Attach(this);

        //添加fragment
        arr.add(new FragmentHomepage());
        arr.add(new FragmentInvest());
        arr.add(new FragmentMyAssets());
        arr.add(new FragmentMore());
        Vpadp adp=new Vpadp(getSupportFragmentManager(),arr,this);
        homeActivityViewPager.setAdapter(adp);

    }

    private void ViewPagerJudge() {
        homeActivityViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (homeActivityViewPager.getCurrentItem()==0){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleHomepage);
                    homeActivityRadioHome.setTextColor(Color.BLUE);
                    homeActivityRadioInvestor.setTextColor(Color.BLACK);
                    homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                    homeActivityRadioMore.setTextColor(Color.BLACK);

                }else if (homeActivityViewPager.getCurrentItem()==1){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleInvestor);
                    homeActivityRadioHome.setTextColor(Color.BLACK);
                    homeActivityRadioInvestor.setTextColor(Color.BLUE);
                    homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                    homeActivityRadioMore.setTextColor(Color.BLACK);
                }else if (homeActivityViewPager.getCurrentItem()==2){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleMyAssets);
                    homeActivityRadioHome.setTextColor(Color.BLACK);
                    homeActivityRadioInvestor.setTextColor(Color.BLACK);
                    homeActivityRadioMyAssets.setTextColor(Color.BLUE);
                    homeActivityRadioMore.setTextColor(Color.BLACK);
                }else if (homeActivityViewPager.getCurrentItem()==3){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleMore);
                    homeActivityRadioHome.setTextColor(Color.BLACK);
                    homeActivityRadioInvestor.setTextColor(Color.BLACK);
                    homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                    homeActivityRadioMore.setTextColor(Color.BLUE);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void initView() {
        init();
        //上来让他等于第一个页面
        homeActivityViewPager.setCurrentItem(0);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    private void ButtonLogic() {
        //首页按钮
        homeActivityRadioHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivityViewPager.setCurrentItem(0);
                homeActivityRadioHome.setTextColor(Color.BLUE);
                homeActivityRadioInvestor.setTextColor(Color.BLACK);
                homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                homeActivityRadioMore.setTextColor(Color.BLACK);
            }
        });
        //投资按钮
        homeActivityRadioInvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivityViewPager.setCurrentItem(1);
                homeActivityRadioHome.setTextColor(Color.BLACK);
                homeActivityRadioInvestor.setTextColor(Color.BLUE);
                homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                homeActivityRadioMore.setTextColor(Color.BLACK);
            }
        });
        //我的资产按钮
        homeActivityRadioMyAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivityViewPager.setCurrentItem(2);
                homeActivityRadioHome.setTextColor(Color.BLACK);
                homeActivityRadioInvestor.setTextColor(Color.BLACK);
                homeActivityRadioMyAssets.setTextColor(Color.BLUE);
                homeActivityRadioMore.setTextColor(Color.BLACK);
            }
        });
        //更多按钮
        homeActivityRadioMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivityViewPager.setCurrentItem(3);
                homeActivityRadioHome.setTextColor(Color.BLACK);
                homeActivityRadioInvestor.setTextColor(Color.BLACK);
                homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                homeActivityRadioMore.setTextColor(Color.BLUE);
            }
        });
    }

    private void init() {
        homeActivityTittleBarId = (TextView) findViewById(R.id.homeActivityTittleBarId);
        homeActivityViewPager = (ViewPager) findViewById(R.id.homeActivityViewPager);
        homeActivityRadioGroup = (RadioGroup) findViewById(R.id.homeActivityRadioGroup);
        homeActivityRadioHome = (RadioButton) findViewById(R.id.homeActivityRadioHome);
        homeActivityRadioInvestor = (RadioButton) findViewById(R.id.homeActivityRadioInvestor);
        homeActivityRadioMyAssets = (RadioButton) findViewById(R.id.homeActivityRadioMyAssets);
        homeActivityRadioMore = (RadioButton) findViewById(R.id.homeActivityRadioMore);

    }



    @Override
    public void onGetDataSucess(Bean data) {

    }

    @Override
    public void onGetDataListSucess(List<Bean> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
