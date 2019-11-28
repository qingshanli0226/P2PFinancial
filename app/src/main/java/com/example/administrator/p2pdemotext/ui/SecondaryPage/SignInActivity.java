package com.example.administrator.p2pdemotext.ui.SecondaryPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Base.BaseActivity;
import com.example.administrator.p2pdemotext.Base.UserRegisterBean;
import com.example.administrator.p2pdemotext.Presenter.RegisterPresenter;
import com.example.administrator.p2pdemotext.R;
import com.example.base.IBasePresenter;
import com.gyf.immersionbar.ImmersionBar;

import java.util.HashMap;
import java.util.List;

public class SignInActivity extends BaseActivity<UserRegisterBean> {
    private IBasePresenter<UserRegisterBean> iBasePresenter;
    private TextView activitySignInFinsh;
    private TextView homeActivityTittleBarId;
    private EditText signinActivityEditPhone;
    private EditText signinActivityEditUser;
    private EditText signinActivityEditPass;
    private EditText signinActivityEditRePass;
    private Button signinActivityButton;
    HashMap<String,String> hashMap=new HashMap<>();


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

                        hashMap.put("name",signinActivityEditUser.getText().toString());
                        hashMap.put("password",signinActivityEditPass.getText().toString());
                        hashMap.put("phone",signinActivityEditPhone.getText().toString());

                        iBasePresenter=new RegisterPresenter(hashMap);
                        iBasePresenter.attachView(SignInActivity.this);
                        iBasePresenter.doHttpPostRequest(200);

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
    public void onHttpRequestDataSuccess(int requestCode, UserRegisterBean data) {
        boolean isExist = data.isIsExist();
        if (isExist){
            //失败
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }else {
            //成功
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onGetDataSucess(UserRegisterBean data) {

    }

    @Override
    public void onGetDataListSucess(List<UserRegisterBean> data) {

    }

    @Override
    public void onGetDataFailed(String message) {

    }

}
