package com.bw.jinrong.adapter;

import com.bw.base.BaseHolder;
import com.bw.base.MyBaseAdapter;
import com.bw.jinrong.bean.HomeBean;
import com.bw.jinrong.bean.Product;

import java.util.List;

public class ProductAdapter extends MyBaseAdapter<HomeBean.ProInfoBean> {

    public ProductAdapter(List<HomeBean.ProInfoBean> list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new MyHolder();
    }
}
