package com.example.administrator.p2pdemotext.ui.SecondaryPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.R;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;

public class SignInActivity extends BaseActivity<Object> {
    private TextView activitySignInFinsh;
    private TextView homeActivityTittleBarId;
    private EditText signinActivityEditPhone;
    private EditText signinActivityEditUser;
    private EditText signinActivityEditPass;
    private EditText signinActivityEditRePass;
    private Button signinActivityButton;



    @Override
    protected void initData() {

        ImmersionBar.with(this).init();
        //退出
        activitySignInFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        signinActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signinActivityEditPhone.getText().toString().equals("")||signinActivityEditUser.getText().toString().equals("")||signinActivityEditPass.getText().toString().equals("")||signinActivityEditRePass.getText().toString().equals("")){
                    Toast.makeText(SignInActivity.this, "不可以为空", Toast.LENGTH_SHORT).show();
                }else {
                    if (signinActivityEditPass.getText().toString().equals(signinActivityEditRePass.getText().toString())){
                        Toast.makeText(SignInActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SignInActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {
        activitySignInFinsh = (TextView) findViewById(R.id.activitySignInFinsh);
        homeActivityTittleBarId = (TextView) findViewById(R.id.homeActivityTittleBarId);
        signinActivityEditPhone = (EditText) findViewById(R.id.signinActivityEditPhone);
        signinActivityEditUser = (EditText) findViewById(R.id.signinActivityEditUser);
        signinActivityEditPass = (EditText) findViewById(R.id.signinActivityEditPass);
        signinActivityEditRePass = (EditText) findViewById(R.id.signinActivityEditRePass);
        signinActivityButton = (Button) findViewById(R.id.signinActivityButton);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void onGetDataSucess(Object data) {

    }

    @Override
    public void onGetDataListSucess(List<Object> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }
}
