package com.example.p2pdemo.fragment;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.Random;


import butterknife.BindView;

public class InvestHotFragment extends BaseFragment {
    @BindView(R.id.mFlowLayout)
    TagFlowLayout mFlowLayout;
    //    @BindView(R.id.investhot_rv)
//    RecyclerView investhotRv;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
       mFlowLayout.setAdapter(new TagAdapter(datas) {
           @Override
           public View getView(FlowLayout parent, int position, Object o) {
               TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.investhot_text, mFlowLayout, false);
               Random random = new Random();
               int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
               GradientDrawable drawable = new GradientDrawable();
               drawable.setColor(ranColor);
               drawable.setStroke(1,ranColor);
               drawable.setCornerRadius(10);
               view.setText(o.toString());
               view.setBackground(drawable);
               return view;
           }
       });
       mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
           @Override
           public boolean onTagClick(View view, int position, FlowLayout parent) {
               Toast.makeText(getContext(),datas[position],Toast.LENGTH_SHORT).show();
               return true;
           }
       });
    }

    @Override
    protected void loadData() {

//        InvestHotAdapter investHotAdapter = new InvestHotAdapter(R.layout.item_investhot, datas);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//           if (datas.get(position).length()>5){
//               return 2;
//           }else {
//               return 1;
//           }
//            }
//        });
//        investhotRv.setLayoutManager(gridLayoutManager);
//        investhotRv.setAdapter(investHotAdapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_investhot;
    }
}
