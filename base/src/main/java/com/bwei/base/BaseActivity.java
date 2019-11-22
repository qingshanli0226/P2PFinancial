package com.bwei.base;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bwei.common.NetcomentManager;

public abstract class BaseActivity extends AppCompatActivity implements NetcomentManager.INetConnectListener{
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
        NetcomentManager.getInstance(this).registerNetConnectListener(this);

    }

    protected abstract void initView();

    protected abstract void initDate();

    protected abstract int getLayoutId();

    public boolean isConnected() {
        return NetcomentManager.getInstance(this).isConnectStatus();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }

}
