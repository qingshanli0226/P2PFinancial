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
import com.example.base.presenter.IBasePresenter;
import com.example.commen.P2PError;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

public class InvestFragment extends BaseFragment<InvestProductBean> {
    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;
    private IBasePresenter investPresenter;
    private SegmentTabLayout mTabpageInvest;
    private ViewPager mVpInvest;
    private SlidingTabLayout mMStl;

    @Override
    protected void initData() {
        super.initData();
        investPresenter = new InvestPresenter();
        investPresenter.attachView(this);
        investPresenter.doHttpRequest(0);

        //FlycoTabLabLayout使用
        setFlycoTabLab();
    }

    String[] mTitles = new String[]{getString(R.string.app_fragment_invest_tab_title_all), getString(R.string.app_fragment_invest_tab_title_recommend), getString(R.string.app_fragment_invest_tab_title_hot)};

    //设置FlycoTabLabLayout
    public void setFlycoTabLab() {
        mTabpageInvest.setTabData(mTitles);

        mMStl.setViewPager(mVpInvest);
        mVpInvest.setAdapter(new MypagerAdapter(getFragmentManager()));

    }

    //TODO Tab实现二
    class MypagerAdapter extends FragmentPagerAdapter {

        public MypagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return null;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);

        mTabpageInvest = (SegmentTabLayout) view.findViewById(R.id.tabpage_invest);
        mVpInvest = (ViewPager) view.findViewById(R.id.vp_invest);
        mMStl = (SlidingTabLayout) view.findViewById(R.id.mStl);
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText("投资");
        mIvTitleSetting.setVisibility(View.GONE);

    }


    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List data) {

    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, InvestProductBean data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {

    }


}
