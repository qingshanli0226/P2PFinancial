package com.example.p2pdemo.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p2pdemo.R;
import com.example.p2pdemo.common.BaseFragment;

import butterknife.BindView;



//首页
public class HomeFragment extends BaseFragment {
    @BindView(R.id.iv_title_black)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_title)
    TextView tbTitle;

    @Override
    protected void initTitle() {
        tbTitle.setText("首页");
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }
}
