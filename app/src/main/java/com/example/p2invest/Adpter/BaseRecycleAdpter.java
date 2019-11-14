package com.example.p2invest.adpter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ProductData;
import com.example.p2invest.R;
import com.example.p2invest.custor.AddListProcess;

import java.util.List;

public class BaseRecycleAdpter  extends BaseQuickAdapter<ProductData.DataBean, BaseViewHolder> {
    public BaseRecycleAdpter(int layoutResId, @Nullable List<ProductData.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductData.DataBean item) {
        helper.setText(R.id.p_name,item.getName())
                .setText(R.id.p_money,item.getMoney())
                .setText(R.id.p_yearlv,item.getYearRate())
                .setText(R.id.p_suodingdays,item.getSuodingDays())
        .setText(R.id.p_minzouzi,item.getMinTouMoney())
        .setText(R.id.p_minnum,item.getMemberNum());

        AddListProcess view = helper.getView(R.id.p_progresss);

        view.setMaxProcess(Integer.parseInt(item.getProgress()));
    }
}
