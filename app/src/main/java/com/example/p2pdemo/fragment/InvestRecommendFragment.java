package com.example.p2pdemo.fragment;

import android.widget.Toast;

import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;
import com.example.p2pdemo.adpter.StellarMapAdapter;
import com.leon.stellarmap.lib.StellarMap;

import butterknife.BindView;

public class InvestRecommendFragment extends BaseFragment implements StellarMapAdapter.onItemOnClickListener {
    @BindView(R.id.mStellar)
    StellarMap mStellar;
    //提供装配的数据
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        StellarMapAdapter stellarMapAdapter = new StellarMapAdapter(datas, getContext());
        stellarMapAdapter.registerOnItemOnClickListener(this);
        mStellar.setAdapter(stellarMapAdapter);//设置adapter
        //设置星状图内部padding
        mStellar.setInnerPadding(8,8,8,8);
        //设置布局网格20*20
        mStellar.setRegularity(20,20);
        //设置初始化组
        mStellar.setGroup(0);


    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_investrecommend;
    }

    @Override
    public void itemOnclickListener(String item) {
        Toast.makeText(getActivity(),item, Toast.LENGTH_SHORT).show();
    }
}
