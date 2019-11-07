package com.bwei.p2p.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.R;

public class UserFragment extends BaseFragment {
    private TextView textView;
    private ImageView imageViewLift;
    private ImageView imageViewRight;
    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initDate() {
        setTitles();
    }

    private void setTitles() {
        textView.setText("我的");
        imageViewLift.setVisibility(View.INVISIBLE);
        imageViewRight.setVisibility(View.INVISIBLE);

    }
    protected void initView() {
         textView = mView.findViewById(R.id.tv_title);
        imageViewLift= mView.findViewById(R.id.iv_title_back);
        imageViewRight = mView.findViewById(R.id.iv_title_setting);
    }
}
