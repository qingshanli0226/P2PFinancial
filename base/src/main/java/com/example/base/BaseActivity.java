package com.example.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        initview();
        initdata();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initdata();

    protected abstract void initview();

    protected abstract int getlayout();
    
}
