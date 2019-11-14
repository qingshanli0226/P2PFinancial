package com.bwei.p2p.invest.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.R;
import com.bwei.p2p.util.FlowLayout;

import java.util.Random;

public class InvestHotFragment extends BaseFragment {
    private FlowLayout flowLayout;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private String[] colors=new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C",
    "D","E","F"};
    @Override
    protected int getLayoutId() {
        return R.layout.invest_hot;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initDate() {
        for (int i = 0; i < datas.length; i++) {
            final TextView tv=new TextView(getContext());
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
            marginLayoutParams.rightMargin=10;
            marginLayoutParams.leftMargin=10;
            marginLayoutParams.bottomMargin=10;
            marginLayoutParams.topMargin=10;
            Random random = new Random();
            tv.setLayoutParams(marginLayoutParams);
            tv.setPadding(5,5,5,5);
            tv.setTextSize(15f);
            Log.i("ssss", "initDate: "+datas[i]);
            tv.setText(""+datas[i]);
            final StringBuffer sb = new StringBuffer();
            sb.append("#");
            for (int j = 0; j < 6; j++) {
                sb.append(colors[random.nextInt(colors.length)]);
            }
            tv.setTextColor(Color.parseColor(sb.toString()));
            sb.deleteCharAt(3);
            sb.append(colors[random.nextInt(colors.length-3)]);
            tv.setBackgroundColor(Color.parseColor(sb.toString()));
            tv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN :
                            tv.setBackgroundColor(Color.WHITE);
                            Toast.makeText(mActivity, ""+tv.getText().toString(), Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_UP:
                            tv.setBackgroundColor(Color.parseColor(sb.toString()));break;
                    }
                    return true;
                }
            });
            flowLayout.addView(tv);

        }

    }

    @Override
    protected void initView() {
        flowLayout=mView.findViewById(R.id.invest_hot_flowLayout);
    }
}
