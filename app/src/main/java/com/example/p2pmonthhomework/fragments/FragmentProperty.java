package com.example.p2pmonthhomework.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.common.view.MyTitlebar;
import com.example.p2pmonthhomework.R;

import org.jetbrains.annotations.NotNull;

public class FragmentProperty extends BaseFragment implements MyTitlebar.OnTitleClick {

    private MyTitlebar mtitlebar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    public void initView(@NotNull View view) {
        setHeadLayout(view);
    }

    private void setHeadLayout(View view) {
        mtitlebar = view.findViewById(R.id.mtitlebar);

        setTitlebar();
    }

    private void setTitlebar() {
        mtitlebar.showSettingImage();
        mtitlebar.setOnTitleClick(this);
        mtitlebar.setTitle("我的资产");
    }

    @Override
    public void initData() {

    }

    @Override
    public void OnBackClick() {

    }

    @Override
    public void OnSettingClick() {

    }
}
