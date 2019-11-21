package com.example.p2pfiancial.userinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.p2pfiancial.bean.LoginBean;
import com.example.p2pfiancial.cache.ACache;

import java.util.LinkedList;

public class UserInfoManager {
    private static UserInfoManager instance;
    private Context mContext;
    private ACache aCache;
    private SharedPreferences sharedPreferences;
    private LinkedList<UserInfoStatusListener> userInfoStatusListeners = new LinkedList<>();

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {
        if (instance == null) {
            instance = new UserInfoManager();
        }
        return instance;
    }

    public void init(Context context, ACache aCache) {
        this.mContext = context;
        this.aCache = aCache;
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }


    /**
     * 用户登录状态
     *
     * @return
     */
    public boolean isLogin() {
        return sharedPreferences.getBoolean("isLogin", false);
    }

    /**
     * 退出登录
     */
    public void unLogout() {
        if (isLogin()) { //登录状态
            //清除用户信息
            aCache.remove("userInfo");

            //清除手势信息
            clearGestureLock();

            //清除登录状态
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove("isLogin");
            edit.apply();

            //退出登录
            for (UserInfoStatusListener listener : userInfoStatusListeners) {
                listener.onLoginStatus(isLogin(), readUserInfo());
            }
        }
    }

    /**
     * 保存用户信息
     *
     * @param dataBean
     */
    public void saveUserInfo(LoginBean.DataBean dataBean) {
        if (aCache != null) {
            aCache.put("userInfo", dataBean); //缓存用户信息
            //设置登录状态
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isLogin", true);
            edit.apply();

            //登录状态监听
            for (UserInfoStatusListener listener : userInfoStatusListeners) {
                listener.onLoginStatus(isLogin(), readUserInfo());
            }
        }
    }

    /**
     * 读取用户信息
     *
     * @return
     */
    public LoginBean.DataBean readUserInfo() {
        if (aCache != null && isLogin()) {
            return (LoginBean.DataBean) aCache.getAsObject("userInfo");
        }
        return null;
    }

    /**
     * 保存手势密码 sp存储
     *
     * @param tmpPwd
     */
    @SuppressLint("CommitPrefEdits")
    public void saveGestureLock(String tmpPwd) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("pattern", tmpPwd).apply();

        
    }

    /**
     * 获取手势状态
     *
     * @return
     */
    public boolean isPattern() {
        return readGestureLock() != null;
    }


    /**
     * 读取手势密码 sp
     *
     * @return
     */
    public String readGestureLock() {
        return sharedPreferences.getString("pattern", null);
    }

    /**
     * 清除手势信息
     */
    public void clearGestureLock() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("pattern");
        edit.apply();
    }


    public void registerUserInfoStatusListener(UserInfoStatusListener userInfoStatusListener) {
        if (!userInfoStatusListeners.contains(userInfoStatusListener)) {
            userInfoStatusListeners.add(userInfoStatusListener);
        }
    }

    public void unRegisterUserInfoStatusListener(UserInfoStatusListener userInfoStatusListener) {
        if (userInfoStatusListeners.contains(userInfoStatusListener)) {
            userInfoStatusListeners.remove(userInfoStatusListener);
        }
    }

    //定义用户状态接口
    public interface UserInfoStatusListener {
        void onLoginStatus(boolean isLogin, LoginBean.DataBean dataBean);
//        void onPatternStatus();
    }
}
