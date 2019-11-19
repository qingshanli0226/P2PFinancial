package com.example.administrator.p2pdemotext.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.p2pdemotext.Base.AllBean;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.View.RecyHomeView;

import java.util.ArrayList;

public class InvestAllAdapter extends RecyclerView.Adapter<InvestAllAdapter.Myhoder> {
    ArrayList<AllBean.DataBean> arr;
    Context context;

    public InvestAllAdapter(ArrayList<AllBean.DataBean> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public Myhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.investallad_apterhen,null);
        return new Myhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myhoder myhoder, final int i) {
        myhoder.pMoney.setText(arr.get(i).getMoney());
        myhoder.pYearlv.setText(arr.get(i).getYearRate());
        myhoder.pSuodingdays.setText(arr.get(i).getSuodingDays());
        myhoder.pMinnum.setText(arr.get(i).getMinTouMoney());
        myhoder.pName.setText(arr.get(i).getName());
        myhoder.pMinzouzi.setText(arr.get(i).getMemberNum());
        myhoder.review.setProgress(Integer.parseInt(arr.get(i).getProgress()));
        myhoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huidiao.hui(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }




    class Myhoder extends RecyclerView.ViewHolder{
        private TextView pName;
        private TextView pMoney;
        private TextView pYearlv;
        private TextView pSuodingdays;
        private TextView pMinzouzi;
        private TextView pMinnum;
        private RecyHomeView review;



        public Myhoder(@NonNull View itemView) {
            super(itemView);
            pName = (TextView) itemView.findViewById(R.id.p_name);
            pMoney = (TextView) itemView.findViewById(R.id.p_money);
            pYearlv = (TextView) itemView.findViewById(R.id.p_yearlv);
            pSuodingdays = (TextView) itemView.findViewById(R.id.p_suodingdays);
            pMinzouzi = (TextView) itemView.findViewById(R.id.p_minzouzi);
            pMinnum = (TextView) itemView.findViewById(R.id.p_minnum);
            review = (RecyHomeView) itemView.findViewById(R.id.review);
        }
    }
    public interface Huidiao{
        public void hui(int i);

    }
    Huidiao huidiao;

    public void setHuidiao(Huidiao huidiao) {
        this.huidiao = huidiao;
    }
}
