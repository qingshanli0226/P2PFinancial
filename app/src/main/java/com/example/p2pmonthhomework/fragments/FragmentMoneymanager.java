package com.example.p2pmonthhomework.fragments;

import android.view.View;

import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.common.ErrorCodes;
import com.example.common.view.MyLoadingPage;
import com.example.p2pmonthhomework.MoneymanagePresenter;
import com.example.p2pmonthhomework.R;
import com.example.p2pmonthhomework.bean.MoneymanageBean;

import java.util.Iterator;
import java.util.List;

public class FragmentMoneymanager extends BaseFragment implements IBaseView<MoneymanageBean> {

    private int MONEYMANAGE_REQUEST_CODE = 200;
    private MyLoadingPage mLoadingPage;
    private IBasePresenter iBasePresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_moneymanager;
    }

    @Override
    public void initView(View view) {
        mLoadingPage = view.findViewById(R.id.mLoadingPage);
    }

    @Override
    public void initData() {
        iBasePresenter = new MoneymanagePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData(MONEYMANAGE_REQUEST_CODE);
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


            }
        }
    }

    @Override
    public void onGetDataListSuccess(int requestCode, List<MoneymanageBean> data) {

    }

    @Override
    public void onGetDataFailed(int requestCode, ErrorCodes codes) {

    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }

}
