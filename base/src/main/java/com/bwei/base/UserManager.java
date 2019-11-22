package com.bwei.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.bwei.base.bean.User;

public class UserManager {
    private static UserManager instance;
    private Context context;
    SharedPreferences sp ;
    private UserManager() {
    }
    public void init(Context context){
        this.context=context;
        sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
    }
    public static UserManager getInstance() {
        if (instance==null){
            instance=new UserManager();
        }
        return instance;
    }
    //保存用户信息
    public void saveUser(User user){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",user.getName());
        editor.putString("imageurl",user.getImageurl());
        editor.putBoolean("iscredit", user.isCredit());
        editor.putString("phone",user.getPhone());
        editor.commit();//必须提交，否则保存不成功
    }
//判断是否是重置手势密码
    public void saveTrueGesture(){
        sp.edit().putBoolean("isGseture",true).commit();
    }
    public void saveFalseGesture(){
        sp.edit().putBoolean("isGseture",false).commit();
    }
    public boolean readIsGesture(){
        return  sp.getBoolean("isGseture", false);
    }
    //保存手势信息
    public void saveGesture(String s){
        sp.edit().putString("gseture",s).commit();
    }
    public String readGesture(){
        String s = sp.getString("gseture", null);
        return s;
    }
    //读取用户信息
    public User readUser(){
        User user = new User();
        user.setName(sp.getString("name",""));
        user.setImageurl(sp.getString("imageurl", ""));
        user.setPhone(sp.getString("phone", ""));
        user.setCredit(sp.getBoolean("iscredit",false));
        return user;
    }
    public void saveIcon(String uri){
        sp.edit().putString("imgname",uri).apply();
    }
    public String readIcon(){
       return sp.getString("imgname","无");
    }
}
