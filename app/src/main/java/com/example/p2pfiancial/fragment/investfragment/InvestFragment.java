package com.example.p2pfiancial.fragment.investfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class InvestFragment extends BaseFragment {

    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;

    private SegmentTabLayout mTabpageInvest; //方案一
    private ViewPager mVpInvest;
    private SlidingTabLayout mMStl;

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] mTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);

        mTabpageInvest = view.findViewById(R.id.tabpage_invest);
        mMStl = view.findViewById(R.id.mStl);
        mVpInvest = view.findViewById(R.id.vp_invest);
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText(getResources().getString(R.string.app_fragment_invest_top_title));
        mIvTitleSetting.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        mTitles = new String[]{"全部理财", "推荐理财", "热门理财"};
        fragmentList.add(new ProductListFragment());
        fragmentList.add(new ProductRecommondFragment());
        fragmentList.add(new ProductHotFragment());

        //FlycoTabLabLayout使用
        mVpInvest.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mMStl.setViewPager(mVpInvest);
    }


    //TODO Tab实现二
    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
