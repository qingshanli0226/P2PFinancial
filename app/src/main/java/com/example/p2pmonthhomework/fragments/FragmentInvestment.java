package com.example.p2pmonthhomework.fragments;

import android.view.View;

import com.example.base.BaseFragment;
import com.example.common.view.MyLoadingPage;
import com.example.common.view.MyTitlebar;
import com.example.p2pmonthhomework.R;

import org.jetbrains.annotations.NotNull;

public class FragmentInvestment extends BaseFragment {

    private MyTitlebar mtitlebar;

    private MyLoadingPage mLoadingPage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_investment;
    }

    @Override
    public void initView(@NotNull View view) {
        mLoadingPage = view.findViewById(R.id.mLoadingPage);
        mtitlebar = view.findViewById(R.id.mtitlebar);

        setTitlebar();
    }

    private void setTitlebar() {
        mtitlebar.setTitle("投资");
    }

    @Override
    public void initData() {
       mLoadingPage.startLoadingAnimation();
    }
}
