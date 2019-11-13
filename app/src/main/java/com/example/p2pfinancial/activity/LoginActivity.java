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
import com.example.p2pfinancial.R;

public class LoginActivity extends BaseActivity {

    EditText mUser;
    EditText mPassWord;
    Button mLogin;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mUser = findViewById(R.id.et_login_user);
        mPassWord = findViewById(R.id.et_login_pwd);
        mLogin = findViewById(R.id.btn_login);
    }

    @Override
    public void initData() {
        super.initData();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUser.getText().toString();
                String pwd = mPassWord.getText().toString();
                if (user.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences user1 = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = user1.edit();
                String user2 = user1.getString("user", "");
                String password = user1.getString("password", "");
                edit.putBoolean("isLogin", true);
                edit.apply();

                if (user2.equals(user) && pwd.equals(password)) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}
