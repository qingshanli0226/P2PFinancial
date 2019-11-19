package com.bw.jinrong.adapter;

import android.view.View;
import android.widget.TextView;
import com.bw.base.BaseHolder;
import com.bw.common.UIUtils;
import com.bw.jinrong.R;
import com.bw.jinrong.bean.Product;
import com.bw.view.RoundProgress;

class MyHolder extends BaseHolder<Product> {

    private View view;

    @Override
    protected View initView() {
        view = View.inflate(UIUtils.getContext(), R.layout.item_product_list,null);
        return view;
    }

    @Override
    protected void refreshData() {
        Product data = this.getData();

        TextView p_name = view.findViewById(R.id.p_name);
        TextView p_money = view.findViewById(R.id.p_money);
        TextView p_yearlv = view.findViewById(R.id.p_yearlv);
        TextView p_suodingdays = view.findViewById(R.id.p_suodingdays);
        TextView p_minzouzi = view.findViewById(R.id.p_minzouzi);
        TextView p_minnum = view.findViewById(R.id.p_minnum);
        RoundProgress p_progresss = view.findViewById(R.id.p_progresss);

        p_minnum.setText(data.memberNum);
        p_minzouzi.setText(data.minTouMoney);
        p_money.setText(data.money);
        p_name.setText(data.name);
        p_progresss.setProgress(Integer.parseInt(data.progress));
        p_suodingdays.setText(data.suodingDays);
        p_yearlv.setText(data.yearRate);

    }
}
