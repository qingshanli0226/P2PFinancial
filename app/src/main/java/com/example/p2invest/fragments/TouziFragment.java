package com.example.p2invest.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.base.BaseFragment;
import com.example.p2invest.Adpter.TabPageAdpter;
import com.example.p2invest.R;
import com.example.p2invest.TouziTabFg.AllProductFragment;
import com.example.p2invest.TouziTabFg.TuijianFragment;
import com.example.p2invest.TouziTabFg.HotFragment;

import java.util.ArrayList;

public class TouziFragment  extends BaseFragment {
    private TextView tvTitle;
    private ImageView ivTitleSetting;
    private ViewPager viewPager;


    @Override
    public void initData() {

        tvTitle.setText(R.string.touzi);

        ivTitleSetting.setVisibility(View.GONE);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(""+R.string.all);
        strings.add(""+R.string.tuijian);
        strings.add(""+R.string.remen);

        fragments.add(new AllProductFragment());
        fragments.add(new TuijianFragment());
        fragments.add(new HotFragment());


        viewPager.setAdapter(new TabPageAdpter(getChildFragmentManager(),fragments,strings));
    }

    @Override
    public void initView() {
        tvTitle=view.findViewById(R.id.tvtitle);
        ivTitleSetting=view.findViewById(R.id.ivtitlesetting);
        viewPager=view.findViewById(R.id.vp);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.fragment_touzi;
    }
}
