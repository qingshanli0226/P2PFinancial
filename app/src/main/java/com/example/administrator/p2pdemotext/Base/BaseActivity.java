package com.example.administrator.p2pdemotext.Base;

import android.support.v7.app.AppCompatActivity;

import com.example.base.IBaseView;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    @Override
    public void onGetDataSucess(Object data) {

    }

    @Override
    public void onGetDataListSucess(List data) {

    }


    @Override
    public void onGetDataFailed(String message) {

    }


}
