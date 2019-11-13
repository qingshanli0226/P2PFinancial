package com.example.p2pfiancial.fragment.investfragment.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.p2pfiancial.R;
import com.example.p2pfiancial.bean.InvestProductBean;
import com.example.p2pfiancial.common.RoundProgress;

import java.util.List;

public class ProductAdapter extends BaseQuickAdapter<InvestProductBean.DataBean, BaseViewHolder> {
    public ProductAdapter(@Nullable List<InvestProductBean.DataBean> data) {
        super(R.layout.fragment_invest_product_list_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvestProductBean.DataBean item) {
        helper.setText(R.id.p_minnum, item.getMemberNum());
        helper.setText(R.id.p_minzouzi, item.getMinTouMoney());
        helper.setText(R.id.p_money, item.getMoney());
        helper.setText(R.id.p_name, item.getName());
        helper.setText(R.id.p_suodingdays, item.getSuodingDays());
        helper.setText(R.id.p_yearlv, item.getYearRate());

        //自定义view
        RoundProgress roundProgress = helper.getView(R.id.p_progresss);
        roundProgress.setProgress(Integer.parseInt(item.getProgress()));
    }
}
