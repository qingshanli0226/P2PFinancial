package com.bw.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import butterknife.ButterKnife;
import com.bw.common.ActivityInstanceManager;
import com.loopj.android.http.AsyncHttpClient;

import java.security.Key;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);

        //将当前的activity添加到ActivityManager中
        new ActivityInstanceManager().addActivity(this);

        initTitle();

        initData();

    }

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    public AsyncHttpClient client = new AsyncHttpClient();

    //启动新的activity
    public void getToActivity(Class Activity,Bundle bundle){
        Intent intent = new Intent(this,Activity);
        //携带数据
        if (bundle != null && bundle.size() != 0){
            intent.putExtra("data",bundle);
        }

        startActivity(intent);

    }

    //销毁当前的activity
    public void removeCurrentActivity(){
        new ActivityInstanceManager().removeActivity(this);
    }

    //销毁所有的activity
    public void removeAll(){
        new ActivityInstanceManager().finishAllActivity();
    }

    //保存用户信息
    public void saveUser(Key user){

    }

    //读取用户信息

}
