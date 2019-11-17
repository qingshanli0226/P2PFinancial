package com.example.p2invest.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.base.BaseFragment;
import com.example.p2invest.adpter.TabPageAdpter;
import com.example.p2invest.R;
import com.example.p2invest.invest.AllProductFragment;
import com.example.p2invest.invest.HotFragment;
import com.example.p2invest.invest.RecommendFragment;

import java.util.ArrayList;

public class TouziFragment extends BaseFragment{
    private TextView tvTitle;
    private ImageView ivTitleSetting;
    private ViewPager viewPager;
    private TextView tvtitle;
    private ImageView ivtitlesetting;
    private RadioButton tab1;
    private RadioButton tab2;
    private RadioButton tab3;
    private RadioGroup gr;
    private ViewPager vp;


    @Override
    protected void initData() {

        tvTitle.setText(R.string.touzi);

        ivTitleSetting.setVisibility(View.GONE);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("" + R.string.all);
        strings.add("" + R.string.tuijian);
        strings.add("" + R.string.remen);

        fragments.add(new AllProductFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new HotFragment());


        viewPager.setAdapter(new TabPageAdpter(getChildFragmentManager(), fragments, strings));
    }

    @Override
    public void initView() {
        tvTitle = view.findViewById(R.id.tvtitle);
        ivTitleSetting = view.findViewById(R.id.ivtitlesetting);
        viewPager = view.findViewById(R.id.vp);

        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);
        gr = view.findViewById(R.id.gr);

    }

    @Override
    public void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                  tab1.setChecked(true);
                }else  if (position == 1) {
                    tab2.setChecked(true);
                }else  if (position == 2) {
                    tab3.setChecked(true);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.tab1){
                    viewPager.setCurrentItem(0);
                }else if (i==R.id.tab2){
                    viewPager.setCurrentItem(1);
                }else if (i==R.id.tab3){
                    viewPager.setCurrentItem(2);
                }

            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_touzi;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

}
