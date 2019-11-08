package com.example.p2pfinancial.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.p2pfinancial.R;
import com.example.p2pfinancial.bean.AllInvestBean;

import java.nio.file.Path;
import java.util.List;

public class AllInvestAdapter extends BaseAdapter {

    Context context;
    List<AllInvestBean> dataList;

    public AllInvestAdapter(Context context, List<AllInvestBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        allinvestHolder allinvestHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.allinvest_relerview_layout, null);
            allinvestHolder = new allinvestHolder();
            allinvestHolder.allinvest_memberNum = convertView.findViewById(R.id.allinvest_memberNum);
            allinvestHolder.allinvest_suodingDays = convertView.findViewById(R.id.allinvest_suodingDays);
            allinvestHolder.allinvest_yearRate = convertView.findViewById(R.id.allinvest_yearRate);
            allinvestHolder.allinvest_minTouMoney = convertView.findViewById(R.id.allinvest_minTouMoney);
            allinvestHolder.allinvest_money = convertView.findViewById(R.id.allinvest_money);
            allinvestHolder.allinvest_name = convertView.findViewById(R.id.allinvest_name);
            convertView.setTag(allinvestHolder);
        } else {
            allinvestHolder = (AllInvestAdapter.allinvestHolder) convertView.getTag();
        }

        if (allinvestHolder != null) {
            AllInvestBean allInvestBean = dataList.get(position);
            allinvestHolder.allinvest_memberNum.setText(allInvestBean.getMemberNum());
            allinvestHolder.allinvest_suodingDays.setText(allInvestBean.getSuodingDays());
            allinvestHolder.allinvest_yearRate.setText(allInvestBean.getYearRate());
            allinvestHolder.allinvest_minTouMoney.setText(allInvestBean.getMinTouMoney());
            allinvestHolder.allinvest_money.setText(allInvestBean.getMoney());
            allinvestHolder.allinvest_name.setText(allInvestBean.getName());
        }

        return convertView;
    }

    class allinvestHolder {
        TextView allinvest_name;
        TextView allinvest_money;
        TextView allinvest_minTouMoney;
        TextView allinvest_yearRate;
        TextView allinvest_suodingDays;
        TextView allinvest_memberNum;
    }
}
