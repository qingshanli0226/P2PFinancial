package com.example.p2pfiancial.fragment.investfragment.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.util.ShapeUtils;

import java.util.List;
import java.util.Random;

public class ProductHotAdapter extends BaseQuickAdapter<InvestProductBean.DataBean, BaseViewHolder> {
    public ProductHotAdapter(@Nullable List<InvestProductBean.DataBean> data) {
        super(R.layout.fragment_invest_product_hot_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestProductBean.DataBean item) {
        TextView textView = helper.getView(R.id.tv_textContent);
        textView.setText(item.getName());

        //随机颜色
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        textView.setBackground(ShapeUtils.getRoundRectDrawable(8,  ranColor, true, 1));

        helper.addOnClickListener(R.id.tv_textContent);
    }
}
