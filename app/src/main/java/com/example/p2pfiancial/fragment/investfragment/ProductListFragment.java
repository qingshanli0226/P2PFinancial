package com.example.p2pfiancial.fragment.investfragment;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.base.presenter.IBasePresenter;
import com.example.commen.P2PError;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.fragment.investfragment.adapter.ProductListAdapter;

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
        //调用接口方法, 返回给Activity
        onRequestDataListener.onRequestDataSuccess(data);

        //设置适配器 及数据
        mLvProductList.setAdapter(new ProductListAdapter(data.getData()));
    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {
        super.onHttpRequestDataFailed(requestCode, error);
    }

    //两个Fragment之间如何传递数据
    // https://yq.aliyun.com/articles/385215?spm=a2c4e.11153940.0.0.74df4b49Dh417c
    // 1. 定义通信接口
    private onRequestDataListener onRequestDataListener;

    public interface onRequestDataListener {
        void onRequestDataSuccess(InvestProductBean data);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        onRequestDataListener = (ProductListFragment.onRequestDataListener) context;
    }
}
