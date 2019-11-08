package com.example.p2pmonthhomework.fragments;

import android.view.View;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.common.view.MyTitlebar;
import com.example.p2pmonthhomework.R;

import org.jetbrains.annotations.NotNull;

public class Fragmentmore extends BaseFragment {

    private MyTitlebar mtitlebar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void initView(@NotNull View view) {
        mtitlebar = view.findViewById(R.id.mtitlebar);

        setTitlebar();
    }

    private void setTitlebar() {
        mtitlebar.setTitle("更多");
    }

    @Override
    public void initData() {

    }


}
