package jni.example.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import jni.example.common.ActivityManager;
import jni.example.common.NetConnectManager;
import jni.example.common.PageManager;

public abstract class BaseActivity extends AppCompatActivity implements IActivity, NetConnectManager.INetConnectListener,IView {
    PageManager pageManager;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        if(RelativeLayoutID()!=0){
            relativeLayout = findViewById(RelativeLayoutID());
            pageManager = new PageManager(this);
            pageManager.setRelativeLayout(relativeLayout);
        }
        setWindow();
        ActivityManager.addActivity(this);

        init();
        initData();
        //注册listener，监听当前网络连接的变化
        NetConnectManager.getInstance().registerNetConnectListener(this);
    }

    @Override
    public int RelativeLayoutID() {
        return 0;
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
    public boolean isConnected() {
        return NetConnectManager.getInstance().getConnectStatus();
    }

    @Override
    public void onConnected() {
    }

    @Override
    public void onDisConnected() {
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    @Override
    public void showLoading() {
        pageManager.showLoading();
    }

    @Override
    public void hideLoading() {
        pageManager.hideLoading();
    }

    @Override
    public void showErrorPage() {
        pageManager.showErrorPage();
    }

    @Override
    public void hideErrorPage() {
        pageManager.hideErrorPage();
    }

    @Override
    public void showNotNetWorkPage() {
        pageManager.showNotNetWorkPage();
    }

    @Override
    public void hideNotNetWorkPage() {
        pageManager.hideErrorPage();
    }

    @Override
    public void onGetDataFailed(String msg) {

    }

    @Override
    public void onGetDataSuccess(Object data) {

    }

    @Override
    public void onGetDataListSuccess(List data) {

    }

    @Override
    public void onPostDataSuccess(Object data) {

    }

    @Override
    public void onPostDataFailed(String handleError) {

    }
}
