package com.example.month6.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.common.diyviews.baseclass.BaseFragment;
import com.example.month6.R;
import com.example.month6.view.activirys.GestureActivity;

import butterknife.BindView;

public class MoreFragment extends BaseFragment {

    @BindView(R.id.resetGesture)
    TextView resetGesture;

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
    }

}
