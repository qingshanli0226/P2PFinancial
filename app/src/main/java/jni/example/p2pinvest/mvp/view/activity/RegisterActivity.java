package jni.example.p2pinvest.mvp.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import jni.example.base.BaseActivity;
import jni.example.base.IPresenter;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.UserRegisterBean;
import jni.example.p2pinvest.mvp.presenter.RegisterPresenter;
import jni.example.p2pinvest.view.TopBar;

public class RegisterActivity extends BaseActivity {

    private IPresenter iPresenter;
    private HashMap<String,String> map = new HashMap<>();
    private TopBar topBarRegister;
    private EditText etRegisterNumber;
    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;
    private Button btnRegister;

    @Override
    public int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void init() {
        topBarRegister = (TopBar) findViewById(R.id.top_bar_register);
        etRegisterNumber = (EditText) findViewById(R.id.et_register_number);
        etRegisterName = (EditText) findViewById(R.id.et_register_name);
        etRegisterPwd = (EditText) findViewById(R.id.et_register_pwd);
        etRegisterPwdagain = (EditText) findViewById(R.id.et_register_pwdagain);
        btnRegister = (Button) findViewById(R.id.btn_register);

        topBarRegister.setTopBarText(5);
        topBarRegister.setOnMyClicklistener(new TopBar.onMyClicklistener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initData() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();

                //2.所填写的信息不能为空
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdAgain)) {
                    Toast.makeText(RegisterActivity.this, "填写的信息不能为空", Toast.LENGTH_SHORT).show();
                } else if (!pwd.equals(pwdAgain)) {//2.两次密码必须一致
                    Toast.makeText(RegisterActivity.this, "两次填写的密码不一致", Toast.LENGTH_SHORT).show();
                    etRegisterPwd.setText("");
                    etRegisterPwdagain.setText("");
                }
                if (!isConnected()) {
                    Toast.makeText(RegisterActivity.this, "请先检查网络问题", Toast.LENGTH_SHORT).show();
                    return;
                }
                map.put("name", name);
                map.put("password", pwd);
                map.put("phone", number);
                iPresenter = new RegisterPresenter(map);
                iPresenter.attachView(RegisterActivity.this);
                iPresenter.postData();
            }
        });
    }

    @Override
    public void onPostDataSuccess(Object data) {
        UserRegisterBean userRegisterBean = (UserRegisterBean) data;
        if(userRegisterBean.isIsExist())
            Toast.makeText(this, "该用户已被注册", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();
    }

}
