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
    private TextView tvtitle;
    private ImageView ivtitlesetting;


    @Override
    public void initData(View view) {

        tvtitle=view.findViewById(R.id.tvtitle);
        ivtitlesetting=view.findViewById(R.id.ivtitlesetting);

        tvtitle.setText("投资");

        ivtitlesetting.setVisibility(View.GONE);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.touzi;
    }
}
