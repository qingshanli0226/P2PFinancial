package com.bwei.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bwei.base.bean.User;
import com.bwei.common.NetcomentManager;

public abstract class BaseActivity extends AppCompatActivity implements NetcomentManager.INetConnectListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        float density = dm.density;
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
        float smallestWidthDP;
        if(widthDP < heightDP) {
            smallestWidthDP = widthDP;
        }else {
            smallestWidthDP = heightDP;
        }

        initView();
        initDate();
        ActivityInstanceManager.addActivity(this);
        NetcomentManager.getInstance(this).registerNetConnectListener(this);

    }

    protected abstract void initView();

    protected abstract void initDate();

    protected abstract int getLayoutId();

    public boolean isConnected() {
        return NetcomentManager.getInstance(this).isConnectStatus();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
    //保存用户信息
    public void saveUser(User user){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",user.getName());
        editor.putString("imageurl",user.getImageurl());
        editor.putBoolean("iscredit", user.isCredit());
        editor.putString("phone",user.getPhone());
        editor.commit();//必须提交，否则保存不成功
    }

    //读取用户信息
    public User readUser(){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        User user = new User();
        user.setName(sp.getString("name",""));
        user.setImageurl(sp.getString("imageurl", ""));
        user.setPhone(sp.getString("phone", ""));
        user.setCredit(sp.getBoolean("iscredit",false));

        return user;
    }
}
