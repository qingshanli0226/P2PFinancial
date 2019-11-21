package com.bw.jinrong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bw.jinrong.R;
import com.bw.jinrong.bean.HomeBean;
import com.bw.jinrong.bean.InvestBean;
import com.bw.view.RoundProgress;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<InvestBean.DataBean> list;

    public ProductAdapter(Context context, ArrayList<InvestBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
//            view = View.inflate(context,R.layout.item_product_list,viewGroup);
            view = LayoutInflater.from(context).inflate(R.layout.item_product_list,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.p_name = view.findViewById(R.id.p_name);
            viewHolder.p_money = view.findViewById(R.id.p_money);
            viewHolder.p_yearlv = view.findViewById(R.id.p_yearlv);
            viewHolder.p_suodingdays = view.findViewById(R.id.p_suodingdays);
            viewHolder.p_minzouzi = view.findViewById(R.id.p_minzouzi);
            viewHolder.p_minnum = view.findViewById(R.id.p_minnum);
            viewHolder.p_progresss = view.findViewById(R.id.p_progresss);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.p_name.setText(list.get(i).getName());
        viewHolder.p_money.setText(list.get(i).getMoney());
        viewHolder.p_yearlv.setText(list.get(i).getYearRate());
        viewHolder.p_suodingdays.setText(list.get(i).getSuodingDays());
        viewHolder.p_minzouzi.setText(list.get(i).getMinTouMoney());
        viewHolder.p_minnum.setText(list.get(i).getMemberNum());
        viewHolder.p_progresss.setProgress(Integer.parseInt(list.get(i).getProgress()));

        return view;
    }

    class ViewHolder{
        TextView p_name;
        TextView p_money;
        TextView p_yearlv;
        TextView p_suodingdays;
        TextView p_minzouzi;
        TextView p_minnum;
        RoundProgress p_progresss;
    }

}
