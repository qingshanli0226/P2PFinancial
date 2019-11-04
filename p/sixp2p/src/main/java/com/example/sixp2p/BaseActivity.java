package com.example.sixp2p;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

    protected abstract void InitView();

    protected abstract void InitData();

    protected abstract void InitTitle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        InitView();
        MyAppManager.getInstance().addApp(this);

        InitTitle();
        InitData();
    }


}