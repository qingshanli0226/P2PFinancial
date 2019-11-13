package com.example.p2pfiancial.fragment.investfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.activity.MainActivity;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.fragment.investfragment.adapter.ProductHotAdapter;
import com.example.p2pfiancial.util.UIUtils;

public class ProductHotFragment extends BaseFragment {
    private RecyclerView mFlowHot;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest_product_hot;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mFlowHot = (RecyclerView) view.findViewById(R.id.flow_hot);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        mFlowHot.setLayoutManager(manager);
    }

    @Override
    protected void initTopTitle() {
    }

    @Override
    protected void initData() {
        super.initData();
        //从Activity拿fragment中的数据
        final InvestProductBean data = ((MainActivity) getActivity()).getInvestProductData();
        ProductHotAdapter hotAdapter = new ProductHotAdapter(data.getData());
        mFlowHot.setAdapter(hotAdapter);

        //点击吐司
        hotAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String name = data.getData().get(position).getName();
                UIUtils.toast(name, false);
            }
        });
    }
}
