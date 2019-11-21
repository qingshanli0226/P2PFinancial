package com.bw.jinrong.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.bw.base.BaseFragment;
import com.bw.jinrong.R;
import com.bw.jinrong.activity.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment {

    private View view;

    @Override
    protected void initView() {
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("更多");
    }

    @Override
    protected void initData() {
        view = getBaseView();

        TextView tv_more_regist = view.findViewById(R.id.tv_more_regist);
        tv_more_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });

    }

    @Override
    protected int setView() {
        return R.layout.fragment_more;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
