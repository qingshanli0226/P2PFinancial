package com.example.month6.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.month6.R;
import com.example.month6.view.activirys.MoneyPeopleSetActivity;
import com.example.month6.view.customviews.CustomTopView;
import com.example.month6.view.customviews.OnTopClickListener;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class MoneyFragment extends BaseFragment {

    @BindView(R.id.moneyTopView)
    CustomTopView moneyTopView;

    public MoneyFragment(Context fragmentContext) {
        super(fragmentContext);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        moneyTopView.setOnTopClickListener(new OnTopClickListener() {
            @Override
            public void onBackButClick(View v) {

            }

            @Override
            public void onSetButClick(View v) {
                Intent intent = new Intent(fragmentContext, MoneyPeopleSetActivity.class);
                startActivity(intent);
            }
        });
    }

}
