package com.example.administrator.p2pdemotext.Fragment.FragmentInvestinsidePage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.Base.BaseFrament;
import com.example.administrator.p2pdemotext.R;

public class FragmentInvestAll extends BaseFragment {
    private TextView fragmentinvestTextviewPao;
    private RecyclerView fragmentinvestAllRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragmentinvestall;
    }

    @Override
    protected void initData() {
        super.initData();
        fragmentinvestTextviewPao.setSelected(true);

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        fragmentinvestTextviewPao = (TextView) view.findViewById(R.id.fragmentinvestTextviewPao);
        fragmentinvestAllRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentinvestAllRecyclerView);

    }

    @Override
    protected void initTopTitle() {

    }
}
