package com.example.month6.view.fragments;

import android.content.Context;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.month6.R;
import com.example.month6.presenter.HomePresenter;

public class MoreFrag extends BaseFragment {

    public MoreFrag(Context fragmentContext) {
        super(fragmentContext);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.more_frag;
    }


    @Override
    protected DiyPresenter getPresenters() {
        return new HomePresenter();
    }

    @Override
    public void setDataSuccess(Object object) {

    }
}
