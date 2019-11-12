package com.example.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.MyAppManager;

public abstract class BaseActivity extends AppCompatActivity  {

    protected abstract void InitView();

    protected abstract void InitData();

    protected abstract void InitTitle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        InitView();
        MyAppManager.getInstance().addApp(this);

        InitData();
        InitTitle();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAppManager.getInstance().remove(this);
    }
}