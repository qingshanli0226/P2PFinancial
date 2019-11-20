package com.example.month6.view.activirys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.common.diyviews.presenter.BasePresenter;
import com.example.common.diyviews.presenter.IBaseView;
import com.example.common.diyviews.utils.TextEmpty;
import com.example.month6.R;
import com.example.month6.view.customviews.CustomTopView;
import com.example.month6.view.customviews.OnTopClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.loginTopView)
    CustomTopView loginTopView;
    @BindView(R.id.loginNameEdit)
    EditText loginNameEdit;
    @BindView(R.id.loginPassEdit)
    EditText loginPassEdit;
    @BindView(R.id.loginBut)
    Button loginBut;
    @BindView(R.id.registerBut)
    Button registerBut;

    BasePresenter<Object> presenter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loginTopView.setOnTopClickListener(new OnTopClickListener() {
            @Override
            public void onBackButClick(View v) {
                finishActivity();
            }

            @Override
            public void onSetButClick(View v) {

            }
        });
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextEmpty.isEditEmpty(loginNameEdit,loginPassEdit)){
                    Toast.makeText(LoginActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
                }else {
                    //

                }
            }
        });
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextEmpty.isEditEmpty(loginNameEdit,loginPassEdit)){
                    Toast.makeText(LoginActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
