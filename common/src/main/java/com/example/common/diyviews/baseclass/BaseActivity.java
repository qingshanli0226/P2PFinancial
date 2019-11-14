package com.example.common.diyviews.baseclass;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.diyviews.singleclass.ActivityManager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //抽象方法,子类设置布局
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //Activity压入栈
        ActivityManager.getInstance().addActivity(this);
        initData();
        initView();
        //动态权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                    }
                });
    }

    protected abstract void initData();

    protected abstract void initView();

    //销毁
    protected void finishActivity() {
        ActivityManager.getInstance().removeTopActivity();
    }

    //退出全部页面
    protected void quitApp() {
        ActivityManager.getInstance().removeAllActivity();
    }

    protected abstract int getLayoutId();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityManager.getInstance().removeActivity(this);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
