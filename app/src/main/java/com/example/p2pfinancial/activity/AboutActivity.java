package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.base.BaseActivity;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;
//硅谷理财介绍页面
public class AboutActivity extends BaseActivity {

    //设置布局
    @Override
    protected int setLayout() {
        return R.layout.activity_about;
    }
    //初始化
    @Override
    protected void initView() {
        TitleBar titleBar = findViewById(R.id.title_about);
        titleBar.setTitleText("关于硅谷理财");
    }

}
