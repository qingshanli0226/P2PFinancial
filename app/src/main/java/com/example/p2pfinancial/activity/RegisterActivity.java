package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.base.BaseActivity;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;

public class RegisterActivity extends BaseActivity {

    TitleBar titleBar;
    EditText mPhone;
    EditText mUser;
    EditText mPassWord;
    EditText mConfirm;

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titlebar_register);
        mPhone = findViewById(R.id.et_phone);
        mUser = findViewById(R.id.et_user);
        mPassWord = findViewById(R.id.et_pwd);
        mConfirm = findViewById(R.id.et_confirm_password);
    }

    @Override
    public void initData() {
        super.initData();
        titleBar.setTitleText("用户注册");
    }
}
