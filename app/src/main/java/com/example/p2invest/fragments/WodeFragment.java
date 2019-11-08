package com.example.p2invest.fragments;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.base.BaseFragment;
import com.example.p2invest.R;

public class WodeFragment  extends BaseFragment {

    private TextView tvtitle;
    private ImageView ivtitlesetting;


    @Override
    public void initData(View view) {
        tvtitle=view.findViewById(R.id.tvtitle);
        ivtitlesetting=view.findViewById(R.id.ivtitlesetting);

        tvtitle.setText("我的资产");
    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.wode;
    }
}

