package jni.example.p2pinvest.mvp.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jni.example.base.BaseActivity;
import jni.example.base.IPresenter;
import jni.example.base.IView;
import jni.example.base.User;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.UserBean;
import jni.example.p2pinvest.manager.LoginManager;
import jni.example.p2pinvest.mvp.presenter.LoginPresenter;
import jni.example.p2pinvest.view.TopBar;

public class LoginActivity extends BaseActivity {

    private IPresenter iPresenter;

    private LoginManager loginManager;
    private TopBar topBar;
    private RelativeLayout rlLogin;
    private TextView tvLoginNumber;
    private EditText etLoginNumber;
    private TextView tvLoginPwd;
    private EditText etLoginPwd;
    private Button btnLogin;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        loginManager = LoginManager.getLoginManager(this);
        topBar = (TopBar) findViewById(R.id.top_bar);
        rlLogin = (RelativeLayout) findViewById(R.id.rl_login);
        tvLoginNumber = (TextView) findViewById(R.id.tv_login_number);
        etLoginNumber = (EditText) findViewById(R.id.et_login_number);
        tvLoginPwd = (TextView) findViewById(R.id.tv_login_pwd);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        topBar.setTopBarText(4);
        topBar.setOnMyClicklistener(new TopBar.onMyClicklistener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        iPresenter = new LoginPresenter();
        iPresenter.attachView(this);
    }

    @Override
    public void initData() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = etLoginNumber.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(pwd)) {
                    if (iPresenter instanceof LoginPresenter) {
                        LoginPresenter loginPresenter = (LoginPresenter) LoginActivity.this.iPresenter;
                        loginPresenter.setPhone(number);
                        loginPresenter.setPassword(pwd);
                    }
                    if(!isConnected()){
                        Toast.makeText(LoginActivity.this, "当前没有网络,无法登录", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    iPresenter.postData();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onPostDataSuccess(Object data) {
        UserBean userBean = (UserBean) data;
        if(!userBean.isSuccess()){
            Toast.makeText(this, "密码输入错误", Toast.LENGTH_SHORT).show();
            return;
        }
        UserBean.DataBean dataBean = userBean.getData();
        Log.d("lhf",dataBean.toString());
        User user = new User();
        user.setImageurl(dataBean.getImageurl());
        user.setName(dataBean.getName());
        user.setPhone(dataBean.getPhone());
        if (dataBean.getIscredit().equals("true")) {
            user.setCredit(true);
        } else {
            user.setCredit(false);
        }
        saveUser(user);
        loginManager.login();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onPostDataFailed(String handleError) {
        Toast.makeText(this, "请求错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detachView();
    }
}
