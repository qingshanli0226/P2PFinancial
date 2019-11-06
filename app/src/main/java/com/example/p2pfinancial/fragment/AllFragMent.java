package com.example.p2pfinancial.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.base.IBaseView;
import com.example.p2pfinancial.presenter.AllInvestPresenter;
import com.example.p2pfinancial.R;
import com.example.p2pfinancial.bean.AllInvestBean;

import java.util.List;

public class AllFragMent extends Fragment implements IBaseView<AllInvestBean> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);

        AllInvestPresenter allInvestPresenter = new AllInvestPresenter();
        allInvestPresenter.attachView(this);
        allInvestPresenter.getAllInest(100);

        return view;
    }

    @Override
    public void onGetDataSucess(int requestCode, AllInvestBean data) {
            Log.e("####",data.toString()+"");

    }

    @Override
    public void onGetDataListSucess(int requestCode, List<AllInvestBean> data) {
        for (int i = 0; i < data.size(); i++) {
            AllInvestBean allInvestBean = data.get(i);
            Log.e("####", allInvestBean.toString()+"");
        }
    }

    @Override
    public void onGetDataFailed(int requestCode, String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new AllInvestPresenter().detachView();
    }
}
