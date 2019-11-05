package com.example.p2pfiancial.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;

public class InvestFragment extends BaseFragment {
    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText("投资");
        mIvTitleSetting.setVisibility(View.GONE);

    }
}
