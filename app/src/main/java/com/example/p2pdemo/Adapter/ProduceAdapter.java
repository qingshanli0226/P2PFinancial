package com.example.p2pdemo.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.common.Bean.InvestBean;
import com.example.p2pdemo.CustomView.MyProView;
import com.example.p2pdemo.R;

public class ProduceAdapter extends BaseAdapter {
    private Context context;
    private InvestBean datas;
    public ProduceAdapter(Context context, InvestBean data) {
        this.context=context;
        this.datas=data;
    }

    @Override
    public int getCount() {
        return datas.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return datas.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.produec_item, null);
       TextView name = view.findViewById(R.id.Produce_Name);
        TextView money = view.findViewById(R.id.Produce_Money);
        TextView year = view.findViewById(R.id.Produce_yearRote);
        TextView day = view.findViewById(R.id.Produce_day);
        TextView minDay = view.findViewById(R.id.Produce_minDay);
        TextView memberNum = view.findViewById(R.id.Produce_memeberDay);
        MyProView proView = view.findViewById(R.id.proView);



        InvestBean.DataBean dataBean = datas.getData().get(position);
        name.setText(dataBean.getName());
        money.setText(dataBean.getMoney());
        year.setText(dataBean.getYearRate());
        day.setText(dataBean.getSuodingDays());
        minDay.setText(dataBean.getMinTouMoney());
        memberNum.setText(dataBean.getMemberNum());
        proView.getProgress(dataBean.getProgress());





        return view;
    }


//    class MyHolder{
//
//        TextView name,money,year,day,minDay,memeberNum;
//
//    }
}
