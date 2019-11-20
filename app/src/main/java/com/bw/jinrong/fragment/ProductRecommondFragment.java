package com.bw.jinrong.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;

import android.widget.TextView;
import com.bw.base.BaseFragment;
import com.bw.common.UIUtils;
import com.bw.jinrong.R;
import com.bw.view.StellarMap;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductRecommondFragment extends BaseFragment {

    private View view;

    private String[] datas = new String[]{"新手福利计划","财神道90天计划","硅谷计划","30天理财计划","180天理财计划","月月升","中情局投资商业经营","大学老师购买车辆","屌丝下海经商计划","美人鱼影视拍摄投资","Android培训老师自己周转","养猪场扩大经营","旅游公司扩大规模","摩托罗拉洗钱计划","铁路局回款计划","屌丝迎娶白富美计划"};

    private String[] oneDatas = new String[datas.length / 2];
    private String[] twoDatas = new String[datas.length - datas.length / 2];

    private Random random = new Random();

    @Override
    protected void initData() {
        view = getBaseView();

        for (int i = 0; i < datas.length; i++) {
            if (i < datas.length / 2){
                oneDatas[i] = datas[i];
            }else {
                twoDatas[i - datas.length / 2] = datas[i];
            }
        }

        StellarAdapter adapter = new StellarAdapter();
        StellarMap stellar_map = view.findViewById(R.id.stellar_map);
        stellar_map.setAdapter(adapter);

        int leftPadding = UIUtils.dp2px(10);
        int topPadding = UIUtils.dp2px(10);
        int rightPadding = UIUtils.dp2px(10);
        int bottomPadding = UIUtils.dp2px(10);
        stellar_map.setInnerPadding(leftPadding,topPadding,rightPadding,bottomPadding);

        stellar_map.setRegularity(5,7);

        stellar_map.setGroup(0,true);
    }

    @Override
    protected int setView() {
        return R.layout.fragment_product_recommond;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

    private class StellarAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            if (group == 0){
                return datas.length / 2;
            }else {
                return datas.length - datas.length / 2;
            }
        }

        @Override
        public View getView(int group, int position, View convertView) {
            final TextView textView = new TextView(getActivity());

            if (group == 0){
                textView.setText(oneDatas[position]);
            }else {
                textView.setText(twoDatas[position]);
            }

            textView.setTextSize(UIUtils.dp2px(5) + UIUtils.dp2px(random.nextInt(10)));

            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            textView.setTextColor(Color.rgb(red,green,blue));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIUtils.toast(textView.getText().toString(),false);
                }
            });

            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0){
                return 1;
            }else {
                return 0;
            }
        }
    }
}
