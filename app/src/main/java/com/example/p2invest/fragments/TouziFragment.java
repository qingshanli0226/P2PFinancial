package com.example.p2invest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.base.BaseFragment;
import com.example.p2invest.R;

public class TouziFragment  extends BaseFragment {
    private TextView tv_title;
    private ImageView iv_title_setting;

    @Override
    protected View getcontentview(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.touzi, container, false);

        tv_title=view.findViewById(R.id.tv_title);
        iv_title_setting=view.findViewById(R.id.iv_title_setting);

        tv_title.setText("投资");

        iv_title_setting.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }
}
