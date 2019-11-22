package com.example.administrator.p2pdemotext.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.example.administrator.p2pdemotext.Adapter.Vpadp;
import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.Fragment.FragmentInvestinsidePage.FragmentInvestAll;
import com.example.administrator.p2pdemotext.Fragment.FragmentInvestinsidePage.FragmentInvestHot;
import com.example.administrator.p2pdemotext.Fragment.FragmentInvestinsidePage.FragmentInvestRecommend;
import com.example.administrator.p2pdemotext.R;

import java.util.ArrayList;

public class FragmentInvest extends BaseFragment<Object> {
    ArrayList<Fragment> arr=new ArrayList<>();
    private RadioButton fragmentinvestRadioButtonAll;
    private RadioButton fragmentinvestRadioButtonRecommend;
    private RadioButton fragmentinvestRadioButtonHot;
    private ViewPager fragmentinvestViewPager;


    @Override
    protected void initData() {
        super.initData();

        //VIewPager的切换
        ViewPagerSwitch();
        //先清空一下arr防止重复添加
        arr.clear();

        arr.add(new FragmentInvestAll());
        arr.add(new FragmentInvestRecommend());
        arr.add(new FragmentInvestHot());

        //让viewpage加载的数据不再销毁
        fragmentinvestViewPager.setOffscreenPageLimit(3);

        Vpadp adp=new Vpadp(getFragmentManager(),arr,getContext());
        fragmentinvestViewPager.setAdapter(adp);

    }

    private void ViewPagerSwitch() {
        //点击按钮让他切换
        fragmentinvestRadioButtonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentinvestViewPager.setCurrentItem(0);
            }
        });

        fragmentinvestRadioButtonRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentinvestViewPager.setCurrentItem(1);
            }
        });

        fragmentinvestRadioButtonHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentinvestViewPager.setCurrentItem(2);
            }
        });

        fragmentinvestViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (fragmentinvestViewPager.getCurrentItem()==0){
                    //这个是让radio控件文字颜色改变
                    fragmentinvestRadioButtonAll.setTextColor(Color.GREEN);
                    fragmentinvestRadioButtonRecommend.setTextColor(Color.BLACK);
                    fragmentinvestRadioButtonHot.setTextColor(Color.BLACK);
                    //这个是让radio控件背景颜色改变
                    fragmentinvestRadioButtonAll.setBackgroundColor(Color.BLUE);
                    fragmentinvestRadioButtonRecommend.setBackgroundColor(Color.WHITE);
                    fragmentinvestRadioButtonHot.setBackgroundColor(Color.WHITE);

                }else if (fragmentinvestViewPager.getCurrentItem()==1){
                    //这个是让radio控件文字颜色改变
                    fragmentinvestRadioButtonAll.setTextColor(Color.BLACK);
                    fragmentinvestRadioButtonRecommend.setTextColor(Color.GREEN);
                    fragmentinvestRadioButtonHot.setTextColor(Color.BLACK);
                    //这个是让radio控件背景颜色改变
                    fragmentinvestRadioButtonAll.setBackgroundColor(Color.WHITE);
                    fragmentinvestRadioButtonRecommend.setBackgroundColor(Color.BLUE);
                    fragmentinvestRadioButtonHot.setBackgroundColor(Color.WHITE);

                }else if (fragmentinvestViewPager.getCurrentItem()==2){
                    //这个是让radio控件文字颜色改变
                    fragmentinvestRadioButtonAll.setTextColor(Color.BLACK);
                    fragmentinvestRadioButtonRecommend.setTextColor(Color.BLACK);
                    fragmentinvestRadioButtonHot.setTextColor(Color.GREEN);
                    //这个是让radio控件背景颜色改变
                    fragmentinvestRadioButtonAll.setBackgroundColor(Color.WHITE);
                    fragmentinvestRadioButtonRecommend.setBackgroundColor(Color.WHITE);
                    fragmentinvestRadioButtonHot.setBackgroundColor(Color.BLUE);

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
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {





        fragmentinvestRadioButtonAll = (RadioButton) view.findViewById(R.id.fragmentinvestRadioButtonAll);
        fragmentinvestRadioButtonRecommend = (RadioButton) view.findViewById(R.id.fragmentinvestRadioButtonRecommend);
        fragmentinvestRadioButtonHot = (RadioButton) view.findViewById(R.id.fragmentinvestRadioButtonHot);
        fragmentinvestViewPager = (ViewPager) view.findViewById(R.id.fragmentinvestViewPager);

    }

    @Override
    protected void initTopTitle() {

    }
}
