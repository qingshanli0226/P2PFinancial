package com.example.p2invest.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.p2invest.R;
import com.example.p2invest.manager.MD5Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends BaseActivity {
    private TextView tvtitle;
    private ImageView ivtitlesetting;
    private EditText etloginnumber;
    private EditText etloginpwd;
    private Button btnlogin;
    private ImageView ivtitleback;

    @Override
    protected void initListener() {
        ivtitleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = etloginnumber.getText().toString();
                String pass = etloginpwd.getText().toString();
                if (!number.isEmpty()&& !pass.isEmpty()){
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.setTimeout(2000);
                    RequestParams params = new RequestParams();
                    params.put("phone",number);
                    params.put("password", MD5Utils.MD5(pass));
                    client.post("http://169.254.44.116:8080/P2PInvest/login",params,new AsyncHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, String content) {
                            super.onSuccess(statusCode, content);
                            if (statusCode==200){
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();


                                SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = login.edit();
                                edit.putBoolean("b",true);
                                edit.commit();

                                finish();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void initdata() {
        ivtitleback.setVisibility(View.VISIBLE);
        tvtitle.setText("用户登录");
        ivtitlesetting.setVisibility(View.GONE);

    }

    @Override
    protected void initview() {
        tvtitle=findViewById(R.id.tvtitle);
        ivtitlesetting=findViewById(R.id.ivtitlesetting);
        etloginnumber=findViewById(R.id.et_login_number);
        etloginpwd=findViewById(R.id.et_login_pwd);
        btnlogin=findViewById(R.id.btn_login);
        ivtitleback=findViewById(R.id.iv_title_back);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    private void initView() {

    }
}
