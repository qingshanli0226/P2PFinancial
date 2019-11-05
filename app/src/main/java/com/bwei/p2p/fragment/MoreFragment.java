package com.bwei.p2p.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.R;

public class MoreFragment extends BaseFragment {
    private TextView textView;
    private ImageView iv_l;
    private ImageView iv_r;
    private ToggleButton more;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }
    @Override
    protected void initDate() {
        setTitles();
    }

    private void setTitles() {
        textView.setText("更多");
        iv_l.setVisibility(View.INVISIBLE);
        iv_r.setVisibility(View.INVISIBLE);

    }
    protected void initView() {
        textView = mView.findViewById(R.id.tv_title);
        iv_l= mView.findViewById(R.id.iv_title_back);
        iv_r = mView.findViewById(R.id.iv_title_setting);
        more=mView.findViewById(R.id.toggle_more);
        more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){


                }            }
        });
    }
}
