package com.bwei.p2p.invest.Fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.base.BaseFragment;
import com.bwei.p2p.R;
import com.bwei.p2p.util.StellarLayout;

import java.util.Random;

public class InvestreCommendFragment extends BaseFragment {
    private StellarLayout stellar;
    //提供装配的数据
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private String[] oneDatas = new String[datas.length / 2];
    private String[] twoDatas = new String[datas.length - datas.length / 2];
    private Random random = new Random();
    @Override
    protected int getLayoutId() {
        return R.layout.invest_commend;
    }
    @Override
    protected void initDate() {
        for (int i = 0; i < datas.length; i++) {
            if (i < datas.length / 2) {
                oneDatas[i] = datas[i];
            } else {
                twoDatas[i - datas.length / 2] = datas[i];
            }
        }

        StellarAdapter adapter = new StellarAdapter();
        stellar.setAdapter(adapter);

        int leftPadding = 15;
        int topPadding = 15;
        int rightPadding = 15;
        int bottomPadding = 15;
        stellar.setInnerPadding(leftPadding, topPadding, rightPadding, bottomPadding);

        //必须调用如下的两个方法，否则stellarMap不能显示数据
        //设置显示的数据在x轴、y轴方向上的稀疏度
        stellar.setRegularity(5, 7);
        //设置初始化显示的组别，以及是否需要使用动画
        stellar.setGroup(0, true);

    }

    protected void initView() {
        stellar=mView.findViewById(R.id.invest_commend_stellar);
    }
    class StellarAdapter implements StellarLayout.Adapter {

        //获取组的个数
        @Override
        public int getGroupCount() {
            return 2;
        }

        //返回每组中显示的数据的个数
        @Override
        public int getCount(int group) {
            if (group == 0) {
                return datas.length / 2;
            } else {
                return datas.length - datas.length / 2;
            }
        }

        //返回具体的view.
        //position:不同的组别，position都是从0开始。
        @Override
        public View getView(int group, int position, View convertView) {
            final TextView tv = new TextView(getActivity());

            //设置属性
            //设置文本的内容
            if (group == 0) {
                tv.setText(oneDatas[position]);
            } else {
                tv.setText(twoDatas[position]);
            }
            //设置字体的大小
            tv.setTextSize(10 + random.nextInt(10)*2);
            //设置字体的颜色
            int red = random.nextInt(211);//00 ~ ff ; 0 ~ 255
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            tv.setTextColor(Color.rgb(red, green, blue));

            //设置TextView的点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });

            return tv;
        }

        //返回下一组显示平移动画的组别。查看源码发现，此方法从未被调用。所以可以不重写
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        //返回下一组显示缩放动画的组别。
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0)
                return 1;
            else
                return 0;
        }
    }

}
