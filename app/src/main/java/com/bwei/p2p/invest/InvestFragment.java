package com.bwei.p2p.invest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.R;
import com.bwei.p2p.invest.Fragment.InvestAllFragment;
import com.bwei.p2p.invest.Fragment.InvestreCommendFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class InvestFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageViewLift;
    private ImageView imageViewRight;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private List<Fragment> list;
    private String[] titleList =new String[]{"全部理财","推荐理财","热门理财"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }
    @Override
    protected void initDate() {
        setTitles();
        setviewPager();
    }

    private void setviewPager() {
//        表格
        viewPager.setAdapter(new  MyViewPagerAdapter(getChildFragmentManager(),list));
        slidingTabLayout.setViewPager(viewPager,titleList);
        slidingTabLayout.setCurrentTab(0);

    }

    private void setTitles() {
        textView.setText("投资");
        imageViewLift.setVisibility(View.INVISIBLE);
        imageViewRight.setVisibility(View.INVISIBLE);

    }
    protected void initView() {
        textView = mView.findViewById(R.id.tv_title);
        imageViewLift= mView.findViewById(R.id.iv_title_back);
        imageViewRight = mView.findViewById(R.id.iv_title_setting);
        slidingTabLayout=mView.findViewById(R.id.invest_tab);
        viewPager=mView.findViewById(R.id.invest_vp);
        list=new ArrayList<>();
//        三个布局
        list.add(new InvestAllFragment());
        list.add(new InvestreCommendFragment());
        list.add(new InvestAllFragment());

    }
}
