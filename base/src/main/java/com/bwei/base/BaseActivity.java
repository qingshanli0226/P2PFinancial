package com.bwei.base;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        float density = dm.density;
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
        float smallestWidthDP;
        if(widthDP < heightDP) {
            smallestWidthDP = widthDP;
        }else {
            smallestWidthDP = heightDP;
        }

        initView();
        initDate();
        ActivityInstanceManager.addActivity(this);

    }

    protected abstract void initView();

    protected abstract void initDate();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
}
