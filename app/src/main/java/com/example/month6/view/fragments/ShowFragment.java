package com.example.month6.view.fragments;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.common.diyviews.baseclass.BaseViewPagerAdapert;
import com.example.month6.R;
import com.example.month6.view.customviews.CustomTopView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class ShowFragment extends BaseFragment {

    @BindView(R.id.showTopView)
    CustomTopView showTopView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public ShowFragment(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected void initData() {

        Log.e("xxxx","运行ShowFragmentinitData");
        ArrayList<Fragment> list=new ArrayList<>();
        list.add(new ShowAllFragment(fragmentContext));
        list.add(new ShowSuggestFragment(fragmentContext));
        list.add(new ShowHotFragment(fragmentContext));

        ArrayList<String> strs=new ArrayList<>();
        strs.add( fragmentContext.getResources().getString(R.string.showAll));
        strs.add(fragmentContext.getResources().getString(R.string.showSuggest));
        strs.add(fragmentContext.getResources().getString(R.string.showHot));

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new BaseViewPagerAdapert(getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                list,
                strs,
                fragmentContext));
        //不会重新创建子Fragment
        viewPager.setOffscreenPageLimit(3);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initView() {

    }

}
