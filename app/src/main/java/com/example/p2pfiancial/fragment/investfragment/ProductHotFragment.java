package com.example.p2pfiancial.fragment.investfragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.fragment.investfragment.adapter.ProductHotAdapter;
import com.example.p2pfiancial.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductHotFragment extends BaseFragment implements InvestFragment.OnRequestDataListener {
    private RecyclerView mFlowHot;
    private List<InvestProductBean.DataBean> data = new ArrayList<>();
    private ProductHotAdapter hotAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InvestFragment) getParentFragment()).registerListener(this);
    }

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
        hotAdapter = new ProductHotAdapter(data);
        //从Activity拿fragment中的数据
        data.addAll(((InvestFragment) getParentFragment()).getInvestProductData());

        mFlowHot.setAdapter(hotAdapter);

        //点击吐司
        hotAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String name = data.get(position).getName();
                UIUtils.toast(name, false);
            }
        });
    }

    @Override
    public void onDataReceived(List<InvestProductBean.DataBean> dataBeans) {
        this.data.clear();
        this.data.addAll(dataBeans);
        hotAdapter.notifyDataSetChanged();
    }
}
