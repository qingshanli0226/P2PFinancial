package com.example.p2invest.fragments;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.base.BaseFragment;
import com.example.p2invest.R;

public class WodeFragment  extends BaseFragment {

    private TextView tvTitle;
    private ImageView ivTitleSetting;

    @Override
    public void initData() {

        tvTitle.setText(R.string.wode);
    }

    @Override
    public void initView() {
        tvTitle=view.findViewById(R.id.tvtitle);
        ivTitleSetting=view.findViewById(R.id.ivtitlesetting);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.fragment_wode;
    }
}

