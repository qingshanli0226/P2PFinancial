package com.example.p2pfiancial.fragment.investfragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.fragment.investfragment.adapter.StellarMapAdapter;
import com.leon.stellarmap.lib.StellarMap;

import java.util.ArrayList;
import java.util.List;

public class ProductRecommondFragment extends BaseFragment implements InvestFragment.OnRequestDataListener {
    private StellarMap mStellarMap;
    private List<InvestProductBean.DataBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InvestFragment) getParentFragment()).registerListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest_product_recommond;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mStellarMap = (StellarMap) view.findViewById(R.id.stellar_map);
    }

    @Override
    protected void initTopTitle() {

    }


    @Override
    protected void initData() {
        data = ((InvestFragment) getParentFragment()).getInvestProductData();
        if (data != null) {
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                stringList.add("这是什么" + i);
            }
            for (InvestProductBean.DataBean datum : data) {
                stringList.add(datum.getName());
            }

            mStellarMap.setAdapter(new StellarMapAdapter(getActivity(), stringList));

            //设置布局网格 15*20, 越大分布越平均
            mStellarMap.setRegularity(15, 20);
            //设置初始化数组
            mStellarMap.setGroup(0);
        }
    }

    @Override
    public void onDataReceived(List<InvestProductBean.DataBean> dataBeans) {
        initData();
    }
}
