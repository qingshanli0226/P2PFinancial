package jni.example.p2pinvest.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginManager {
    private static LoginManager loginManager;
    private Context context;
    private SharedPreferences sharedPreferences;
    private LoginManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LoginState", Context.MODE_PRIVATE);
    }

    public static LoginManager getLoginManager(Context context) {
        if(loginManager==null){
            loginManager = new LoginManager(context);
        }
        return loginManager;
    }

    //TODO 判断是否登录
    public boolean isLogin(){
        return sharedPreferences.getBoolean("isLogin", false);
    }

    //TODO 如果登录了赋值
    public void login(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isLogin",true);
        edit.commit();
    }

    //TODO 退出登录
    public void exitLogin(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isLogin",false);
    }
}
