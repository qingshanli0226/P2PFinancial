package com.example.p2pfinancial.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.common.CircleView;
import com.example.p2pfinancial.R;
import com.example.p2pfinancial.bean.AllInvestBean2;

import java.util.List;

//全部理财
public class AllInvestAdapter extends BaseAdapter {

    Context context;
    List<AllInvestBean2.DataBean> dataList;


    public AllInvestAdapter(Context context, List<AllInvestBean2.DataBean> dataList) {
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
            allinvestHolder.mMemberNum = convertView.findViewById(R.id.allinvest_memberNum);
            allinvestHolder.mSuodingDays = convertView.findViewById(R.id.allinvest_suodingDays);
            allinvestHolder.mYearRate = convertView.findViewById(R.id.allinvest_yearRate);
            allinvestHolder.mMinTouMoney = convertView.findViewById(R.id.allinvest_minTouMoney);
            allinvestHolder.mMoney = convertView.findViewById(R.id.allinvest_money);
            allinvestHolder.mName = convertView.findViewById(R.id.allinvest_name);
            allinvestHolder.circleView=convertView.findViewById(R.id.cirleView);
            convertView.setTag(allinvestHolder);
        } else {
            allinvestHolder = (AllInvestAdapter.allinvestHolder) convertView.getTag();
        }

        if (allinvestHolder != null) {
            AllInvestBean2.DataBean allInvestBean = dataList.get(position);
            allinvestHolder.mMemberNum.setText(allInvestBean.getMemberNum());
            allinvestHolder.mSuodingDays.setText(allInvestBean.getSuodingDays());
            allinvestHolder.mYearRate.setText(allInvestBean.getYearRate());
            allinvestHolder.mMinTouMoney.setText(allInvestBean.getMinTouMoney());
            allinvestHolder.mMoney.setText(allInvestBean.getMoney());
            allinvestHolder.mName.setText(allInvestBean.getName());
            allinvestHolder.circleView.setSweepAngle(allInvestBean.getProgress());
        }

        return convertView;
    }

    class allinvestHolder {
        TextView mName;
        TextView mMoney;
        TextView mMinTouMoney;
        TextView mYearRate;
        TextView mSuodingDays;
        TextView mMemberNum;
        CircleView circleView;
    }
}
