package com.example.p2pdemo.adpter;

import android.content.Context;

import com.example.modulebase.BaseHolder;
import com.example.modulebase.BaseListAdapter;
import com.example.p2pdemo.bean.HomeBean;
import com.example.p2pdemo.bean.ProductBean;
import com.example.p2pdemo.holder.InvestAllViewHolder;

import java.util.List;

public class InvestAllAdapter extends BaseListAdapter<ProductBean.DataBean> {


    public InvestAllAdapter(List<ProductBean.DataBean> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<ProductBean.DataBean> geHolder() {
        return new InvestAllViewHolder();
    }
}
