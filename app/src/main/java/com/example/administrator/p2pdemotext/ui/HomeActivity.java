package com.example.administrator.p2pdemotext.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Adapter.Vpadp;
import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.DataClass.Bean;
import com.example.administrator.p2pdemotext.Fragment.FragmentHomepage;
import com.example.administrator.p2pdemotext.Fragment.FragmentInvest;
import com.example.administrator.p2pdemotext.Fragment.FragmentMore;
import com.example.administrator.p2pdemotext.Fragment.FragmentMyAssets;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.View.DIYPop;
import com.gyf.immersionbar.ImmersionBar;


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
    private Button homePageButtonTittle;


    ArrayList<Fragment> arr=new ArrayList<>();


    @Override
    protected void initData() {
        //设置按钮切换到退出登录页面
        homePageButtonTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,LogOutActivity.class);
                startActivity(intent);
            }
        });

        //让viewpage不再销毁
        homeActivityViewPager.setOffscreenPageLimit(4);

        //Viewpager滑动判断
        ViewPagerJudge();
        ImmersionBar.with(this).init();

        //按钮判断
        ButtonLogic();

        //加载页实例化
        LoadingStateWidget load=new LoadingStateWidget();
        load.Attach(this);

        //先清空一下arr防止重复添加fragment
        arr.clear();


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
            boolean a=true;
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (homeActivityViewPager.getCurrentItem()==0){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleHomepage);
                    homeActivityRadioHome.setTextColor(Color.BLUE);
                    homeActivityRadioInvestor.setTextColor(Color.BLACK);
                    homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                    homeActivityRadioMore.setTextColor(Color.BLACK);
                    homePageButtonTittle.setVisibility(View.GONE);
                }else if (homeActivityViewPager.getCurrentItem()==1){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleInvestor);
                    homeActivityRadioHome.setTextColor(Color.BLACK);
                    homeActivityRadioInvestor.setTextColor(Color.BLUE);
                    homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                    homeActivityRadioMore.setTextColor(Color.BLACK);
                    homePageButtonTittle.setVisibility(View.GONE);
                }else if (homeActivityViewPager.getCurrentItem()==2){

                    if (a) {
                        SharedPreferences sp=getSharedPreferences("ssh",0);
                        String login = sp.getString("login", "");
                        if (login.equals("")){
//                            PopupWindow pop=new PopupWindow(HomeActivity.this);
//                            View view=LayoutInflater.from(HomeActivity.this).inflate(R.layout.activity_diypop,null);
//                            pop.setContentView(view);
//                            pop.setHeight(400);
//                            pop.setWidth(800);
//                            pop.showAsDropDown(view,60,800);

                            Log.d("SSh","123");
                            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage("您还没有登录呢!");

                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(HomeActivity.this,DIYPop.class);
                                    startActivity(intent);
                                }
                            });
                            builder.setCancelable(false);
                            builder.create().setCanceledOnTouchOutside(false);
                            builder.show();

                        }else {

                        }
                        a=!a;
                    }
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleMyAssets);
                    homeActivityRadioHome.setTextColor(Color.BLACK);
                    homeActivityRadioInvestor.setTextColor(Color.BLACK);
                    homeActivityRadioMyAssets.setTextColor(Color.BLUE);
                    homeActivityRadioMore.setTextColor(Color.BLACK);
                    homePageButtonTittle.setVisibility(View.VISIBLE);

                }else if (homeActivityViewPager.getCurrentItem()==3){
                    //设置标题
                    homeActivityTittleBarId.setText(R.string.homeActivityTittleMore);
                    homeActivityRadioHome.setTextColor(Color.BLACK);
                    homeActivityRadioInvestor.setTextColor(Color.BLACK);
                    homeActivityRadioMyAssets.setTextColor(Color.BLACK);
                    homeActivityRadioMore.setTextColor(Color.BLUE);
                    homePageButtonTittle.setVisibility(View.GONE);
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
                homePageButtonTittle.setVisibility(View.GONE);
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
                homePageButtonTittle.setVisibility(View.GONE);
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
                homePageButtonTittle.setVisibility(View.VISIBLE);
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
                homePageButtonTittle.setVisibility(View.GONE);
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
        homePageButtonTittle = (Button) findViewById(R.id.homePageButtonTittle);

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
