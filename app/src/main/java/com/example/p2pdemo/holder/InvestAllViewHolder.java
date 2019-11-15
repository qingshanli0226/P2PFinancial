package com.example.p2pdemo.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.modulebase.BaseHolder;
import com.example.modulecommon.ui.RoundProgress;
import com.example.p2pdemo.P2PApplication;
import com.example.p2pdemo.R;
import com.example.p2pdemo.bean.ProductBean;

import butterknife.BindView;

public class InvestAllViewHolder extends BaseHolder<ProductBean.DataBean> {


    @BindView(R.id.investAll_item_name)
    TextView investAllItemName;
    @BindView(R.id.investAll_item_goIn)
    ImageView investAllItemGoIn;
    @BindView(R.id.investAll_item_money)
    TextView investAllItemMoney;
    @BindView(R.id.investAll_item_yearRate)
    TextView investAllItemYearRate;
    @BindView(R.id.investAll_item_suodingDays)
    TextView investAllItemSuodingDays;
    @BindView(R.id.investAll_item_minTouMoney)
    TextView investAllItemMinTouMoney;
    @BindView(R.id.investAll_item_memberNum)
    TextView investAllItemMemberNum;
    @BindView(R.id.investAll_item_progress)
    RoundProgress investAllItemProgress;

    @Override
    protected View initView() {
        return View.inflate(P2PApplication.context, R.layout.item_investall, null);
    }

    @Override
    protected void refreshData() {
        ProductBean.DataBean data = this.getDatas();

        investAllItemName.setText(data.getName());
        investAllItemMemberNum.setText(data.getMemberNum());
        investAllItemMoney.setText(data.getMoney());
        investAllItemMinTouMoney.setText(data.getMinTouMoney());
        investAllItemYearRate.setText(data.getYearRate());
        investAllItemProgress.setProgress(Integer.parseInt(data.getProgress()));
        investAllItemSuodingDays.setText(data.getSuodingDays());
    }
}
