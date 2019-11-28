package com.example.administrator.p2pdemotext.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.Base.LoginRegisterBean;
import com.example.administrator.p2pdemotext.Presenter.LoginRegisterPresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.base.IBasePresenter;
import com.gyf.immersionbar.ImmersionBar;

import java.util.HashMap;
import java.util.List;

public class RegisterActivity extends BaseActivity<LoginRegisterBean> {
    private EditText registerEditTextPhonenum;
    private EditText registerEditTextPassword;
    private IBasePresenter<LoginRegisterBean> iBasePresenter;
    HashMap<String,String> hashMap=new HashMap<>();

    private Button registerButton;

    @Override
    protected void initView() {
        registerEditTextPhonenum = (EditText) findViewById(R.id.registerEditTextPhonenum);
        registerEditTextPassword = (EditText) findViewById(R.id.registerEditTextPassword);
        registerButton = (Button) findViewById(R.id.registerButton);

    }


    @Override
    protected void initData() {
        ImmersionBar.with(RegisterActivity.this).init();

        //登录
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerEditTextPhonenum.getText().toString().equals("") || registerEditTextPassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "用户名或者密码不可以为空", Toast.LENGTH_SHORT).show();
                }else {
                    hashMap.put("phone",registerEditTextPhonenum.getText().toString());
                    hashMap.put("password",registerEditTextPassword.getText().toString());
                    iBasePresenter=new LoginRegisterPresenter(hashMap);
                    iBasePresenter.attachView(RegisterActivity.this);
                    iBasePresenter.doHttpPostRequest(200);

                }
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onGetDataListSucess(List data) {

    }

    @Override
    public void onGetDataSucess(LoginRegisterBean data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, LoginRegisterBean data) {
        if (requestCode==200){
            boolean success = data.isSuccess();
            if (success){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                SharedPreferences sp=getSharedPreferences("ssh",0);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("login","1");
                //异步提交
                edit.apply();
                finish();
            }else {
                Toast.makeText(this, "登录失败请重新登录", Toast.LENGTH_SHORT).show();
                registerEditTextPassword.setText("");
                registerEditTextPhonenum.setText("");
            }
        }else {
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }

    }
}
