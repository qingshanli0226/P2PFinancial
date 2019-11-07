package com.example.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    private int layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout(layout));

        initView();
        initData();
    }

    public void initData() {

    }


    @LayoutRes
    protected abstract int setLayout(int layout);

    protected abstract void initView();


}
