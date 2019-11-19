package com.example.p2invest.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.BaseActivity;
import com.example.net.AppNetConfig;
import com.example.p2invest.R;
import com.example.p2invest.manager.MD5Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RegistActivity extends BaseActivity {
    private ImageView ivtitleback;
    private TextView tvtitle;
    private ImageView ivtitlesetting;
    private EditText etregisternumber;
    private EditText etregistername;
    private EditText etregisterpwd;
    private EditText etregisterpwdagain;
    private Button btnregister;

    @Override
    protected void initListener() {
        ivtitleback.setVisibility(View.VISIBLE);
        ivtitleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvtitle.setText("用户注册");
        ivtitlesetting.setVisibility(View.GONE);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ed1 = etregisternumber.getText().toString().trim();
                String ed2 = etregistername.getText().toString().trim();
                String ed3 = etregisterpwd.getText().toString().trim();
                String ed4 = etregisterpwdagain.getText().toString().trim();

               if (ed1.isEmpty()&&ed2.isEmpty()&&ed3.isEmpty()&&ed4.isEmpty()){
                   Toast.makeText(RegistActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
               }else {
                   if (!ed3.equals(ed4)){
                       Toast.makeText(RegistActivity.this, "两次密码不相同", Toast.LENGTH_SHORT).show();
                       etregisterpwd.setText("");
                       etregisterpwdagain.setText("");
                   }else {
                       AsyncHttpClient client = new AsyncHttpClient();
                       RequestParams params = new RequestParams();
                       params.put("name",ed2);
                       params.put("password", MD5Utils.MD5(ed3));
                       params.put("phone",ed1);
                       client.setTimeout(2000);
                       client.post("http://169.254.44.116:8080/P2PInvest/FeedBack",params,new AsyncHttpResponseHandler(){
                           @Override
                           public void onSuccess(int statusCode, String content) {
                               super.onSuccess(statusCode, content);
                               Log.i("onSuccess", "onSuccess: "+statusCode);

                               if (statusCode==200){
                                   Toast.makeText(RegistActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();

                                   JSONObject object = JSON.parseObject(content);
                                   Boolean exist = object.getBoolean("isExist");
                                   if (exist){
                                       Toast.makeText(RegistActivity.this, "已经注册过", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           }
                           @Override
                           public void onFailure(Throwable error, String content) {
                               super.onFailure(error, content);
                               Toast.makeText(RegistActivity.this, "网络连接失败!", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }
               }
            }
        });
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected void initview() {
        ivtitleback=findViewById(R.id.iv_title_back);
        tvtitle=findViewById(R.id.tvtitle);
        ivtitlesetting=findViewById(R.id.ivtitlesetting);
        etregisternumber=findViewById(R.id.et_register_number);
        etregistername=findViewById(R.id.et_register_name);
        etregisterpwd=findViewById(R.id.et_register_pwd);
        etregisterpwdagain=findViewById(R.id.et_register_pwdagain);
        btnregister=findViewById(R.id.btn_register);

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator agiain
    }
}
