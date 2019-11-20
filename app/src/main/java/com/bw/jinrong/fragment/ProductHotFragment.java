package com.bw.jinrong.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.bw.base.BaseFragment;
import com.bw.base.utils.DrawUtils;
import com.bw.common.UIUtils;
import com.bw.jinrong.R;
import com.bw.view.FlowLayout;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductHotFragment extends BaseFragment {

    private View view;

    private String[] datas = new String[]{"新手福利计划","财神道90天计划","硅谷计划","30天理财计划","180天理财计划","月月升","中情局投资商业经营","大学老师购买车辆","屌丝下海经商计划","美人鱼影视拍摄投资","Android培训老师自己周转","养猪场扩大经营","旅游公司扩大规模","摩托罗拉洗钱计划","铁路局回款计划","屌丝迎娶白富美计划"};

    @Override
    protected void initData() {

        view = getBaseView();

        for (int i = 0; i < datas.length; i++) {
            final TextView textView = new TextView(getContext());

            //设置属性
            textView.setText(datas[i]);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            marginLayoutParams.leftMargin = UIUtils.dp2px(5);
            marginLayoutParams.rightMargin = UIUtils.dp2px(5);
            marginLayoutParams.topMargin = UIUtils.dp2px(5);
            marginLayoutParams.bottomMargin = UIUtils.dp2px(5);
            textView.setLayoutParams(marginLayoutParams);

            int padding = UIUtils.dp2px(5);
            textView.setPadding(padding,padding,padding,padding);

            textView.setTextSize(UIUtils.dp2px(10));

            Random random = new Random();
            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);

            textView.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(Color.rgb(red,green,blue),UIUtils.dp2px(5)),DrawUtils.getDrawable(Color.WHITE,UIUtils.dp2px(5))));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIUtils.toast(textView.getText().toString(),false);
                }
            });

            FlowLayout flow_hot = view.findViewById(R.id.flow_hot);

            flow_hot.addView(textView);

        }
    }

    @Override
    protected int setView() {
        return R.layout.fragment_product_hot;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
