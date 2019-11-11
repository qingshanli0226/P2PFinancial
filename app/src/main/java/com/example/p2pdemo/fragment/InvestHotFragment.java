package com.example.p2pdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InvestHotFragment extends BaseFragment {
    @BindView(R.id.mFlowLayout)
    TagFlowLayout mFlowLayout;
    //    @BindView(R.id.investhot_rv)
//    RecyclerView investhotRv;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        datas.add("新手福利计划");
        datas.add("财神道90天计划");
        datas.add("硅谷计划");
        datas.add("30天理财计划");
        datas.add("180天理财计划");
        datas.add("月月升");
        datas.add("中情局投资商业经营");
        datas.add("大学老师购买车辆");
        datas.add("屌丝下海经商计划");
        datas.add("Android培训老师自己周转");
        datas.add("养猪场扩大经营");
        datas.add("旅游公司扩大规模");
        datas.add("摩托罗拉洗钱计划");
        datas.add("铁路局回款计划");
        datas.add("屌丝迎娶白富美计划");
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
