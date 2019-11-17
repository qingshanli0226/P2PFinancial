package com.example.p2invest.invest;

import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.base.BaseFragment;
import com.example.p2invest.R;
import com.example.p2invest.adpter.StellarMapAdapter;
import com.leon.stellarmap.lib.StellarMap;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment {

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private StellarMap stellMap;
    public List<String> stringList;
    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        stringList=new ArrayList<>();
        for (int i = 0; i <datas.length ; i++) {
            stringList.add(datas[i]);
        }
        stellMap=view.findViewById(R.id.stellMap);
        stellMap.setAdapter(new StellarMapAdapter(getActivity(),stringList));
       /// stellMap.setAdapter(new StellarMap(this,datas));
        //stellMap.setAdapter(new StellarMap.Adapter(this,datas));
        int padding = 30;
        //设置星状图内部padding
        stellMap.setInnerPadding(padding, padding, padding, padding);
        //设置布局网格15*20，越大分布越平均
        stellMap.setRegularity(15, 20);
        //设置初始化组
        stellMap.setGroup(0);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int layoutId() {
        return R.layout.touzi_tuijianfragment;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
