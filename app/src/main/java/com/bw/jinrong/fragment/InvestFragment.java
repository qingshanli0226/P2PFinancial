package com.bw.jinrong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import com.bw.base.BaseFragment;
import com.bw.jinrong.R;
import com.bw.view.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends BaseFragment {

    private View mView;

    @Override
    protected void initData() {
        initFragments();

        MyAdapter adapter = new MyAdapter(getFragmentManager());
//        mView = getBaseView();
        ViewPager vp_invest = mView.findViewById(R.id.vp_invest);
        vp_invest.setAdapter(adapter);

        TabPageIndicator tabpage_invest = mView.findViewById(R.id.tabpage_invest);
        tabpage_invest.setViewPage(vp_invest);

        initTitle();

    }

    private List<Fragment> fragmentList = new ArrayList<>();
    private void initFragments() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommondFragment productRecommondFragment = new ProductRecommondFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();

        fragmentList.add(productListFragment);
        fragmentList.add(productRecommondFragment);
        fragmentList.add(productHotFragment);

    }

    protected void initTitle(){
        ImageView iv_title_back = mView.findViewById(R.id.iv_title_back);
        TextView tv_title = mView.findViewById(R.id.tv_title);
        ImageView iv_title_setting = mView.findViewById(R.id.iv_title_setting);

        iv_title_back.setVisibility(View.GONE);
        tv_title.setText("投资");
        iv_title_setting.setVisibility(View.GONE);

    }

    @Override
    protected int setView() {
        return R.layout.fragment_invest;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
