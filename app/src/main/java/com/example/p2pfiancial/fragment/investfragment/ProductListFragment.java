package com.example.p2pfiancial.fragment.investfragment;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.base.presenter.IBasePresenter;
import com.example.commen.P2PError;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.fragment.investfragment.adapter.ProductAdapter;

public class ProductListFragment extends BaseFragment<InvestProductBean> {

    private RecyclerView mLvProductList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest_product_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        TextView mTvProductTitle = view.findViewById(R.id.tv_product_title);
        mTvProductTitle.setSelected(true); //设置跑马灯

        mLvProductList = (RecyclerView) view.findViewById(R.id.lv_product_list);
        mLvProductList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initTopTitle() {

    }

    @Override
    protected void initData() {
        //获取数据
        IBasePresenter listPresenter = new InvestProductListPresenter();
        listPresenter.attachView(this);
        listPresenter.doHttpRequest(2001);
    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, InvestProductBean data) {
        mLvProductList.setAdapter(new ProductAdapter(data.getData()));
    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {
        super.onHttpRequestDataFailed(requestCode, error);
    }
}
