package jni.example.p2pinvest.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import jni.example.p2pinvest.R;

public class InvestTopBar extends LinearLayout {
    private TextView investAll;
    private TextView investRecommend;
    private TextView investHot;
    private MyOnClickListener listener;

    public void setListener(MyOnClickListener listener) {
        this.listener = listener;
    }

    public InvestTopBar(Context context) {
        super(context);
    }

    public InvestTopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.invest_top_bar, this);
        investAll = (TextView) findViewById(R.id.invest_all);
        investRecommend = (TextView) findViewById(R.id.invest_recommend);
        investHot = (TextView) findViewById(R.id.invest_hot);

        investAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(0);
                setInvestAll();
            }
        });
        investRecommend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(1);
                setInvestRecommend();
            }
        });
        investHot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getIndex(2);
                setInvestHot();
            }
        });



    }

    public interface MyOnClickListener{
        void getIndex(int index);
    }

    private void TextViewunify(){
        investAll.setTextColor(Color.BLACK);
        investAll.setBackgroundColor(Color.WHITE);
        investRecommend.setTextColor(Color.BLACK);
        investRecommend.setBackgroundColor(Color.WHITE);
        investHot.setTextColor(Color.BLACK);
        investHot.setBackgroundColor(Color.WHITE);
    }

    public void setInvestAll() {
        TextViewunify();
        investAll.setTextColor(Color.RED);
        investAll.setBackgroundColor(Color.parseColor("#039BE5"));
    }

    public void setInvestRecommend() {
        TextViewunify();
        investRecommend.setTextColor(Color.RED);
        investRecommend.setBackgroundColor(Color.parseColor("#039BE5"));
    }

    public void setInvestHot() {
        TextViewunify();
        investHot.setTextColor(Color.RED);
        investHot.setBackgroundColor(Color.parseColor("#039BE5"));
    }
}
