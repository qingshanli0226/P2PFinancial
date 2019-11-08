package com.example.p2pdemo.fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;

import butterknife.BindView;

public class InvestAllFragment extends BaseFragment {
    @BindView(R.id.investall_title)
    TextView investallTitle;
    @BindView(R.id.investall_lv)
    ListView investallLv;

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_investall;
    }
}
