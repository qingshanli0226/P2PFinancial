package com.example.p2pfinancial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.IBaseView;
import com.example.common.P2PError;
import com.example.common.TitleBar;
import com.example.p2pfinancial.R;
import com.example.p2pfinancial.presenter.RegisterPresenter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;

//注册页面
public class RegisterActivity extends BaseActivity implements IBaseView {

    TitleBar titleBar;
    EditText mPhone;
    EditText mUser;
    EditText mPassWord;
    EditText mConfirm;
    Button mRegister;

    //设置布局
    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    //初始化控件
    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titlebar_register);
        mPhone = findViewById(R.id.et_phone);
        mUser = findViewById(R.id.et_user);
        mPassWord = findViewById(R.id.et_pwd);
        mConfirm = findViewById(R.id.et_confirm_password);
        mRegister = findViewById(R.id.btn_register);
    }

    RegisterPresenter registerPresenter;

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

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name", user);
                hashMap.put("password", password);
                hashMap.put("phone", phone);
                registerPresenter = new RegisterPresenter(hashMap);
                registerPresenter.registerUser(100);
                registerPresenter.attachView(RegisterActivity.this);


            }
        });

    }

    @Override
    public void onGetDataSucess(int requestCode, Object data) {
    }

    @Override
    public void onPostDataSucess(Object data) {
        try {
            JSONObject jsonObject = new JSONObject(data.toString());
            boolean isExist = jsonObject.getBoolean("isExist");
            if (!isExist) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "账号已存在,注册失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetDataListSucess(int requestCode, List data) {
    }

    @Override
    public void onGetDataFailed(int requestCode, P2PError error) {
    }

    @Override
    public void onLoading() {
    }

    @Override
    public void onStopLoading() {
    }
}
