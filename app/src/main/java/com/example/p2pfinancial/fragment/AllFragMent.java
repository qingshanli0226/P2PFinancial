package com.example.p2pfinancial.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.base.IBaseView;
import com.example.common.LoadingPage;
import com.example.common.P2PError;
import com.example.p2pfinancial.adapter.AllInvestAdapter;
import com.example.p2pfinancial.presenter.AllInvestPresenter;
import com.example.p2pfinancial.R;
import com.example.p2pfinancial.bean.AllInvestBean;

import java.util.ArrayList;
import java.util.List;

public class AllFragMent extends BaseFragment implements IBaseView<AllInvestBean> {


    ListView listView;
    List<AllInvestBean> dataList = new ArrayList<>();
    TextView textView;
    LinearLayout mLayout;
    LoadingPage mLoading;

    @Override
    protected int setLayoutRes() {
        return R.layout.all_fragment;
    }

    @Override
    protected void initView(View view) {
        //初始化控件
        listView = view.findViewById(R.id.lv_allinvest);
        textView = view.findViewById(R.id.tv_slogan);
        mLayout = view.findViewById(R.id.allinvest_frag);
        mLoading = view.findViewById(R.id.loadingPage);
    }

    @Override
    public void initData() {
        //网络请求数据
        AllInvestPresenter allInvestPresenter = new AllInvestPresenter();
        allInvestPresenter.attachView(this);
        allInvestPresenter.getAllInest(100);
        //跑马灯
        textView.setSelected(true);
    }

    //加载中
    @Override
    public void onLoading() {
        mLoading.startLoading(LoadingPage.LOADING_PAGE);
        mLayout.setVisibility(View.INVISIBLE);
    }
    //停止加载
    @Override
    public void onStopLoading() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoading.isSucceed();
                mLayout.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }


    @Override
    public void onGetDataSucess(int requestCode, AllInvestBean data) {
    }

    @Override
    public void onGetDataListSucess(int requestCode, List<AllInvestBean> data) {
        for (int i = 0; i < data.size(); i++) {
            AllInvestBean allInvestBean = data.get(i);
//            Log.e("####", allInvestBean.toString() + "");
            dataList.add(allInvestBean);
            AllInvestAdapter allInvestAdapter = new AllInvestAdapter(getContext(), dataList);
            listView.setAdapter(allInvestAdapter);
        }
    }

    @Override
    public void onGetDataFailed(int requestCode, P2PError error) {
        Log.e("####", error.getErrorMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new AllInvestPresenter().detachView();
    }
}
