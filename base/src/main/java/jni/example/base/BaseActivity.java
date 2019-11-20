package jni.example.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import jni.example.common.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity implements IActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        setWindow();
        ActivityManager.addActivity(this);
        init();
        initData();
    }

    @Override
    public void setWindow() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
    }

    public void saveUser(User user){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",user.getName());
        editor.putString("imageurl",user.getImageurl());
        editor.putBoolean("iscredit", user.isCredit());
        editor.putString("phone",user.getPhone());
        editor.commit();//必须提交，否则保存不成功
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
