package com.example.month6.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.month6.R;
import com.example.month6.view.activirys.MoreAboutP2PActivity;
import com.example.month6.view.activirys.GestureActivity;

import butterknife.BindView;

public class MoreFragment extends BaseFragment {

    @BindView(R.id.resetGesture)
    TextView resetGesture;
    @BindView(R.id.aboutP2P)
    TextView aboutP2P;

    public MoreFragment(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {
        resetGesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragmentContext, GestureActivity.class);
                startActivity(intent);
            }
        });
        aboutP2P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragmentContext, MoreAboutP2PActivity.class);
                startActivity(intent);
            }
        });
    }

}
