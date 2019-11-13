package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;

public class RegisterActivity extends BaseActivity {

    TitleBar titleBar;
    EditText mPhone;
    EditText mUser;
    EditText mPassWord;
    EditText mConfirm;
    Button mRegister;

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
        mRegister = findViewById(R.id.btn_register);
    }

    @Override
    public void initData() {
        super.initData();
        titleBar.setTitleText("用户注册");
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorBlue));
        titleBar.setTitleInterface(new TitleBar.TitleInterface() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = mPhone.getText().toString();
                String user = mUser.getText().toString();
                String password = mPassWord.getText().toString();
                String confirm = mConfirm.getText().toString();

                if (phone.isEmpty() || user.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "两次密码不同", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences user1 = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = user1.edit();
                edit.putString("user", user);
                edit.putString("password", password);
                edit.putString("phone", phone);
                edit.putBoolean("isLogin", false);
                edit.apply();
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
