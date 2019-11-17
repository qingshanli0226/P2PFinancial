package com.example.p2invest.invest;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseFragment;
import com.example.p2invest.R;
import com.example.p2invest.custor.MyFlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Random;

public class HotFragment extends BaseFragment {


    private TagFlowLayout tagFlow;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private Random random;
    private MyFlowLayout mFl;

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        random = new Random();
        mFl=view.findViewById(R.id.mFl);

        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        int a=0,i=0;
        while(a<40){
            ++a;
            i=random.nextInt(datas.length);
            final  TextView textView = new TextView(getActivity());
            textView.setText(datas[i]);
            textView.setTextSize(30);
            int v = new Random().nextInt(255);
            int b = new Random().nextInt(255);
            int c = new Random().nextInt(255);

            textView.setTextColor(Color.rgb(v,b,c));
            textView.setGravity(Gravity.CENTER);

            int w = new Random().nextInt(255);
            int z = new Random().nextInt(255);
            int y = new Random().nextInt(255);


            textView.setBackgroundColor(Color.rgb(w,z,y));
            if(i%3==0){
                textView.setTextSize(20);
            }
            textView.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), ""+textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    textView.setBackgroundColor(Color.WHITE);
                    //textView.setBackgroundColor(Color.rgb(w,z,y));

                }
            });
            mFl.addView(textView,lp);
        }

    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.touzi_hotfragment;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
