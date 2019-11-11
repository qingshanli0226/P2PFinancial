package com.example.p2pdemo.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;

import butterknife.BindView;

//首页
public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_title_black)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @Override
    protected void initTitle() {
        tvTitle.setText(R.string.tab_text3);
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }
}
