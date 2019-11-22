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
import com.example.p2pfinancial.presenter.LoginPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

//登录页面
public class LoginActivity extends BaseActivity implements IBaseView {

    EditText mUser;
    EditText mPassWord;
    Button mLogin;
    TitleBar mTitle;

    //设置布局
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    //初始化
    @Override
    protected void initView() {
        mUser = findViewById(R.id.et_login_user);
        mPassWord = findViewById(R.id.et_login_pwd);
        mLogin = findViewById(R.id.btn_login);
        mTitle = findViewById(R.id.title_login);
    }

    LoginPresenter loginPresenter;

    @Override
    public void initData() {
        super.initData();

        mTitle.setTitleText("登录");
        mTitle.setLeftText("返回");
        mTitle.setLeftTextColor(getResources().getColor(R.color.colorWhite));
        mTitle.setTitleInterface(new TitleBar.TitleInterface() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUser.getText().toString();//用户名
                String pwd = mPassWord.getText().toString();//密码
                if (user.isEmpty() || pwd.isEmpty()) {//不能为空
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("phone", user);
                hashMap.put("password", pwd);
                loginPresenter = new LoginPresenter(hashMap);
                loginPresenter.getLoginData();
                loginPresenter.attachView(LoginActivity.this);
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
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isLogin", true);
                edit.apply();
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
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
