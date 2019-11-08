package com.example.p2pfinancial.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.base.IBaseView;
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
    LinearLayout loading;
    LinearLayout allinvest_frag;
    ImageView imageView;
    AnimationDrawable animationDrawable;

    @Override
    protected int setLayoutRes() {
        return R.layout.all_fragment;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.lv_allinvest);
        textView = view.findViewById(R.id.tv_slogan);
        loading = view.findViewById(R.id.ll_allinvest_loading);
        allinvest_frag = view.findViewById(R.id.allinvest_frag);
        imageView = view.findViewById(R.id.iv_allinvest_loading);
    }

    @Override
    public void initData() {
        AllInvestPresenter allInvestPresenter = new AllInvestPresenter();
        allInvestPresenter.attachView(this);
        allInvestPresenter.getAllInest(100);
        textView.setSelected(true);
    }

    @Override
    public void onGetDataSucess(int requestCode, AllInvestBean data) {
        Log.e("####", data.toString() + "");

    }

    @Override
    public void onGetDataListSucess(int requestCode, List<AllInvestBean> data) {
        for (int i = 0; i < data.size(); i++) {
            AllInvestBean allInvestBean = data.get(i);
            Log.e("####", allInvestBean.toString() + "");
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
    public void onLoading() {

        animationDrawable = (AnimationDrawable) imageView.getBackground();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    @Override
    public void onStopLoading() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (animationDrawable.isRunning()) {
                    allinvest_frag.setVisibility(View.VISIBLE);
                    animationDrawable.stop();
                    loading.setVisibility(View.GONE);
                }
            }
        }, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new AllInvestPresenter().detachView();
    }
}
