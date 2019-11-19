package com.bw.jinrong.adapter;

import com.bw.base.BaseHolder;
import com.bw.base.MyBaseAdapter;
import com.bw.jinrong.bean.Product;

import java.util.List;

public class ProductAdapter extends MyBaseAdapter<Product> {

    public ProductAdapter(List<Product> list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new MyHolder();
    }
}
