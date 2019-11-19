package com.example.p2invest.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.p2invest.R;
import com.example.p2invest.view.RegistActivity;
import com.example.p2invest.view.ResetActivity;

public class MoreFragment extends BaseFragment {
    private TextView tvTitle;
    private ImageView ivTitleSetting;
    private TextView registTv;
    private Switch sWitch;
    private TextView restTv;
    private TextView call;
    private TextView fanKui;
    private TextView shareTv;
    private TextView aboutTv;


    @Override
    protected void initData() {
        tvTitle.setText(R.string.more);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initView() {
        tvTitle = view.findViewById(R.id.tvtitle);
        ivTitleSetting = view.findViewById(R.id.ivtitlesetting);

        registTv = view.findViewById(R.id.registTv);
        sWitch = view.findViewById(R.id.sWitch);
        restTv = view.findViewById(R.id.restTv);
        call = view.findViewById(R.id.call);
        fanKui = view.findViewById(R.id.fanKui);
        shareTv = view.findViewById(R.id.shareTv);
        aboutTv = view.findViewById(R.id.aboutTv);
    }

    @Override
    public void setListener() {
        restTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResetActivity.class);
                startActivity(intent);
            }
        });

        registTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
