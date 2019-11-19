package com.example.p2pmonthhomework.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.common.ErrorCodes;
import com.example.common.view.MyLoadingPage;
import com.example.common.view.MyTabView;
import com.example.common.view.MyTitlebar;
import com.example.p2pmonthhomework.MoneymanagePresenter;
import com.example.p2pmonthhomework.R;
import com.example.p2pmonthhomework.adapter.MyViewPagerAdapter;
import com.example.p2pmonthhomework.bean.MoneymanageBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FragmentInvestment extends BaseFragment implements MyTabView.OnTabClickListener {

    private MyTitlebar mtitlebar;
    private MyTabView mTabView;

    private ViewPager mViewPager;

    private FragmentMoneymanager fragmentMoneymanager;
    private FragmentRecommend fragmentRecommend;
    private FragmentHot fragmentHot;

    private List<Fragment> fragments = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_investment;
    }

    @Override
    public void initView(@NotNull View view) {
        mtitlebar = view.findViewById(R.id.mtitlebar);
        mTabView = view.findViewById(R.id.mTabView);

        mViewPager = view.findViewById(R.id.mViewPager);

        setTitlebar();
    }

    private void setTitlebar() {
        mtitlebar.setTitle("投资");
    }

    @Override
    public void initData() {
        mTabView.setOnTabClickListener(this);

        initViewPager();
    }

    private void initViewPager() {

        fragmentMoneymanager = new FragmentMoneymanager();
        fragmentRecommend = new FragmentRecommend();
        fragmentHot = new FragmentHot();

        fragments.add(fragmentMoneymanager);
        fragments.add(fragmentRecommend);
        fragments.add(fragmentHot);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getChildFragmentManager(), fragments);

        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabView.setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void OnTabClickChanged(int position) {
       mViewPager.setCurrentItem(position);
    }
}
