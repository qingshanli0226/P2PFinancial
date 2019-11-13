package com.example.month6.view.fragments;

import android.content.Context;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.month6.R;
import com.example.month6.view.customviews.CustomTopView;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MoneyFragment extends BaseFragment {

    @BindView(R.id.moneyTopView)
    CustomTopView moneyTopView;

    public MoneyFragment(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money;
    }

    @Override
    protected void initView() {

    }

}
