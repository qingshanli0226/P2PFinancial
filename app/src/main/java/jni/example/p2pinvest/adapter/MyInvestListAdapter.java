package jni.example.p2pinvest.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.Product;
import jni.example.p2pinvest.view.MyProgressBar;

public class MyInvestListAdapter extends BaseAdapter {

    private ArrayList<Product.DataBean> list;

    public MyInvestListAdapter(ArrayList<Product.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size()>0? list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.invest_list_item, null);
            holder.pName = (TextView) convertView.findViewById(R.id.p_name);
            holder.pMoney = (TextView) convertView.findViewById(R.id.p_money);
            holder.pYearlv = (TextView) convertView.findViewById(R.id.p_yearlv);
            holder.pSuodingdays = (TextView) convertView.findViewById(R.id.p_suodingdays);
            holder.pMinzouzi = (TextView) convertView.findViewById(R.id.p_minzouzi);
            holder.pMinnum = (TextView) convertView.findViewById(R.id.p_minnum);
            holder.investAllProgress = (MyProgressBar) convertView.findViewById(R.id.invest_all_progress);
            convertView.setTag(holder);
        }else{
            holder = (MyHolder) convertView.getTag();
        }
        Product.DataBean dataBean = list.get(position);
        holder.pName.setText(dataBean.getName());
        holder.pMoney.setText(dataBean.getMoney());
        holder.pYearlv.setText(dataBean.getYearRate());
        holder.pMinnum.setText(dataBean.getMemberNum());
        holder.pMinzouzi.setText(dataBean.getMinTouMoney());
        holder.pSuodingdays.setText(dataBean.getSuodingDays());
        holder.investAllProgress.setMax(100);
        holder.investAllProgress.setProgress(Integer.parseInt(dataBean.getProgress()));
        holder.investAllProgress.postInvalidate();
        return convertView;
    }

    class MyHolder {
        private TextView pName;
        private TextView pMoney;
        private TextView pYearlv;
        private TextView pSuodingdays;
        private TextView pMinzouzi;
        private TextView pMinnum;
        private MyProgressBar investAllProgress;
    }
}
