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
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.User;
import jni.example.p2pinvest.mvp.presenter.LoginPresenter;
import jni.example.p2pinvest.view.TopBar;

public class LoginActivity extends BaseActivity implements IView<User> {

    private IPresenter iPresenter;

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
        topBar = (TopBar) findViewById(R.id.top_bar);
        rlLogin = (RelativeLayout) findViewById(R.id.rl_login);
        tvLoginNumber = (TextView) findViewById(R.id.tv_login_number);
        etLoginNumber = (EditText) findViewById(R.id.et_login_number);
        tvLoginPwd = (TextView) findViewById(R.id.tv_login_pwd);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        topBar.setTopBarText(4);
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
                if(!TextUtils.isEmpty(number) && !TextUtils.isEmpty(pwd)){
                    if(iPresenter instanceof LoginPresenter){
                        LoginPresenter loginPresenter = (LoginPresenter) LoginActivity.this.iPresenter;
                        loginPresenter.setPhone(number);
                        loginPresenter.setPassword(pwd);
                    }
                    iPresenter.postData();
                }else{
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void hideErrorPage() {

    }

    @Override
    public void showNotNetWorkPage() {

    }

    @Override
    public void hideNotNetWorkPage() {

    }

    @Override
    public void onGetDataFailed(String msg) {

    }

    @Override
    public void onGetDataSuccess(User data) {

    }

    @Override
    public void onGetDataListSuccess(List<User> data) {

    }

    @Override
    public void onPostDataSuccess(User data) {
        User.DataBean data1 = data.getData();
        jni.example.base.User user = new jni.example.base.User();
        user.setImageurl(data1.getImageurl());
        user.setName(data1.getName());
        user.setPhone(data1.getPhone());
        user.setCredit(data1.getIscredit());
        saveUser(data);
        startActivity(new Intent(this,MainActivity.class));
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
