package com.example.p2pmonthhomework.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.common.ErrorCodes;
import com.example.common.view.MyLoadingPage;
import com.example.p2pmonthhomework.MoneymanagePresenter;
import com.example.p2pmonthhomework.R;
import com.example.p2pmonthhomework.adapter.MyRecyclerAdapter;
import com.example.p2pmonthhomework.adapter.MyRecyclerViewHolder;
import com.example.p2pmonthhomework.bean.MoneymanageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FragmentMoneymanager extends BaseFragment implements IBaseView<MoneymanageBean> {

    private int MONEYMANAGE_REQUEST_CODE = 200;
    private MyLoadingPage mLoadingPage;
    private RecyclerView mRecyclerView;
    private IBasePresenter iBasePresenter;
    private List<Map<String,String>> datas = new ArrayList<>();

    private TextView tv_move;
    private MyRecyclerAdapter<Map<String,String>> adapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_moneymanager;
    }

    @Override
    public void initView(View view) {
        mLoadingPage = view.findViewById(R.id.mLoadingPage);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        tv_move = view.findViewById(R.id.tv_move);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter<Map<String,String>>(R.layout.layout_investmental_recycleritem){
            @Override
            public void onBind(MyRecyclerViewHolder holder, int position) {
                Map<String, String> map = datas.get(position);
                holder.setText(R.id.tv_name,map.get("name"));
                holder.setText(R.id.tv_money,map.get("money"));
                holder.setText(R.id.tv_yearRate,map.get("yearRate"));
                holder.setText(R.id.tv_suodingDays,map.get("suodingDays"));
                holder.setText(R.id.tv_minTouMoney,map.get("minTouMoney"));
                holder.setText(R.id.tv_memberNum,map.get("memberNum"));
                holder.setMyRoundProgress(R.id.mRoundView,Integer.parseInt(map.get("progress")));
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        iBasePresenter = new MoneymanagePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData(MONEYMANAGE_REQUEST_CODE);

        tv_move.setSelected(true);
        mLoadingPage.startLoadingAnimation();
    }

    @Override
    public void onGetDataSuccess(int requestCode, MoneymanageBean data) {

        if(requestCode==MONEYMANAGE_REQUEST_CODE){
            List<MoneymanageBean.DataBean> data1 = data.getData();
            Iterator<MoneymanageBean.DataBean> iterator = data1.iterator();
            while (iterator.hasNext()){
                MoneymanageBean.DataBean next = iterator.next();
                String name = next.getName();
                String money = next.getMoney();
                String yearRate = next.getYearRate();
                String suodingDays = next.getSuodingDays();
                String minTouMoney = next.getMinTouMoney();
                String memberNum = next.getMemberNum();
                String progress = next.getProgress();

                Map<String,String> map = new HashMap<>();
                map.put("name",name);
                map.put("money",money);
                map.put("yearRate",yearRate);
                map.put("suodingDays",suodingDays);
                map.put("minTouMoney",minTouMoney);
                map.put("memberNum",memberNum);
                map.put("progress",progress);
                datas.add(map);
            }
            adapter.refresh(datas);

            mLoadingPage.interruptLoadingAnimation();
            mLoadingPage.setLoadingPagedismiss();

            tv_move.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetDataListSuccess(int requestCode, List<MoneymanageBean> data) {

    }

    @Override
    public void onGetDataFailed(int requestCode, ErrorCodes codes) {
        mLoadingPage.interruptLoadingAnimation();
    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }

}
