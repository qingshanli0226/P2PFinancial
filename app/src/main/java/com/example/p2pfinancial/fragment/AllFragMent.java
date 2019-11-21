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
import com.example.p2pfinancial.bean.AllInvestBean2;
import com.example.p2pfinancial.manage.CacheManager;
import com.example.p2pfinancial.adapter.AllInvestAdapter;
import com.example.p2pfinancial.bean.MainBean;
import com.example.p2pfinancial.presenter.AllInvestPresenter;
import com.example.p2pfinancial.R;

import java.util.ArrayList;
import java.util.List;

//全部理财
public class AllFragMent extends BaseFragment implements IBaseView<AllInvestBean2>, CacheManager.IDataRecivedListener {


    private ListView listView;
    private TextView textView;
    private LinearLayout mLayout;
    private LoadingPage mLoading;
    List<AllInvestBean2.DataBean> dataList = new ArrayList<>();

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

    AllInvestPresenter allInvestPresenter;

    @Override
    public void initData() {

        CacheManager.getInstance().registerListener(this);
        //网络请求数据
        allInvestPresenter = new AllInvestPresenter();
        allInvestPresenter.attachView(this);
        allInvestPresenter.getAllInest(100);
        //跑马灯
        textView.setSelected(true);

        AllInvestBean2 allBeanData = CacheManager.getInstance().getAllBeanData();
        if (allBeanData != null) {
            mLoading.isSucceed();
            mLayout.setVisibility(View.VISIBLE);
            getListData(allBeanData);
        }
        mLoading.setAddResetListener(new LoadingPage.addResetListener() {
            @Override
            public void resetLoading() {
                allInvestPresenter.getAllInest(100);
            }
        });
    }

    private void getListData(AllInvestBean2 allBeanData) {
        List<AllInvestBean2.DataBean> data = allBeanData.getData();
        for (int i = 0; i < data.size(); i++) {
            AllInvestBean2.DataBean allInvestBean = data.get(i);
            dataList.add(allInvestBean);
            AllInvestAdapter allInvestAdapter = new AllInvestAdapter(getContext(), dataList);
            listView.setAdapter(allInvestAdapter);
        }
    }

    //加载中
    @Override
    public void onLoading() {
        Log.e("####", "加载页面");
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
    public void onGetDataSucess(int requestCode, AllInvestBean2 data) {
        getListData(data);
    }

    @Override
    public void onPostDataSucess(AllInvestBean2 data) {

    }

    @Override
    public void onGetDataListSucess(int requestCode, List<AllInvestBean2> data) {

    }

    @Override
    public void onGetDataFailed(int requestCode, P2PError error) {
        Log.e("####", "网络请求错误页面");
        mLoading.startLoading(LoadingPage.FAILURE_PAGE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new AllInvestPresenter().detachView();
        CacheManager.getInstance().unregisterListener(this);
    }

    @Override
    public void onDataRecived(MainBean mainBean) {

    }

    @Override
    public void onAllRecived(AllInvestBean2 allInvestBean) {
        getListData(allInvestBean);
    }
}
