package com.bwei.p2p;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bwei.base.BaseActivity;
import com.bwei.base.UserManager;
import com.bwei.p2p.fragment.HomeFragment;
import com.bwei.p2p.fragment.MoreFragment;
import com.bwei.p2p.user.UserFragment;
import com.bwei.p2p.invest.InvestFragment;

public class MainActivity extends BaseActivity {

    private FrameLayout flMain;
    private FragmentTransaction transaction;
    private RadioGroup rg;
    @Override
    protected void initView() {
        flMain = (FrameLayout) findViewById(R.id.main_main);
        rg= (RadioGroup) findViewById(R.id.rg);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("ssss", "onConfigurationChanged: 切换");
    }

    @Override
    protected void initDate() {
        setSelect(0);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_home :
                        setSelect(0);
                        break;
                    case R.id.main_investor :
                        setSelect(1);
                        break;
                    case R.id.main_user :
                        setSelect(2);
                        break;
                    case R.id.main_more :
                        setSelect(3);
                        break;
                    default:
                        Log.i(" sssss ", "onCheckedChanged: 没有"+checkedId);
                        break;
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //提供相应的fragment的显示
    private void setSelect(int i) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        //隐藏所有Fragment的显示
        hideFragments();
        //重置ImageView和TextView的显示状态

        switch (i) {
            case 0 :
                if(homeFragment == null){
                    homeFragment = new HomeFragment();//创建对象以后，并不会马上调用生命周期方法。而是在commit()之后，方才调用
                    transaction.add(R.id.main_main, homeFragment);
                }
                //显示当前的fragment
                transaction.show(homeFragment);
                transaction.commitAllowingStateLoss();//提交事务
                //错误的调用位置
//                homeFragment.show();
                break;
            case 1 :
                if(investFragment == null){
                    investFragment = new InvestFragment();
                    transaction.add(R.id.main_main, investFragment);
                }
                transaction.show(investFragment);
                transaction.commitAllowingStateLoss();//提交事务
                break;
            case 2 :
                showUser();
                break;
            case 3 :
                if(moreFragment == null){
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_main, moreFragment);
                }
                transaction.show(moreFragment);
                transaction.commitAllowingStateLoss();//提交事务
                break;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
            protected void onResume() {
                super.onResume();
                Log.i("SSSS", "onResume: ");
                if (UserManager.getInstance().onBackIsGesture()){
                    if (rg.getCheckedRadioButtonId()==R.id.main_user){
                        Log.i("SSSS", "onResume: 是资产页面");
                        UserManager.getInstance().setonBackIsGesture(false);
                        setSelect(2);
                    }
                }
    }

    private void showUser() {
        UserManager.getInstance().setonBackIsGesture(true);
        if(meFragment == null){
            meFragment = new UserFragment();
            transaction.add(R.id.main_main, meFragment);
        }
        if(!UserManager.getInstance().isLogin()){
            addDialog();
        }else{
//                    登录过
            if (UserManager.getInstance().isGesture()){
//                        有手势密码
                UserManager.getInstance().saveFalseGesture();
                startActivityForResult(new Intent(MainActivity.this,GestureActivity.class),100);
            }else{
                Toast.makeText(this,"还没有设置手势密码请去'更多'设置手势密码",Toast.LENGTH_SHORT).show();
                hideFragments();
                rg.check(R.id.main_home);
            }
        }
    }

    private void hideFragments() {
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(investFragment != null){
            transaction.hide(investFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
        if(moreFragment != null){
            transaction.hide(moreFragment);
        }

    }

    private void addDialog() {
        new AlertDialog.Builder(this).setTitle("提示").setMessage("亲~您还没有登录哦！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));

                    }
                })
                .setCancelable(false)
                .show();
    }
    //重写onKeyUp()，实现连续两次点击方可退出当前应用


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==101){
//          手势  成功
            transaction.show(meFragment);
            transaction.commitAllowingStateLoss();
        }else if(requestCode==100&&resultCode==102){
            if(homeFragment == null){
                homeFragment = new HomeFragment();//创建对象以后，并不会马上调用生命周期方法。而是在commit()之后，方才调用
                transaction.add(R.id.main_main, homeFragment);
            }
            hideFragments();
            transaction.show(homeFragment).commitAllowingStateLoss();
        }
    }

    private boolean flag = true;
    private static final int WHAT_RESET_BACK = 1;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            Log.e("TAG", "handleMessage");
            switch (msg.what) {
                case WHAT_RESET_BACK :
                    flag = true;//复原
                    break;
            }
        }
    };
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && flag){
            Toast.makeText(MainActivity.this, "再点击一次，退出当前应用", Toast.LENGTH_SHORT).show();
            flag = false;
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK,2000);
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private UserFragment meFragment;
    private MoreFragment moreFragment;
    //为了避免出现内存的泄漏，需要在onDestroy()中，移除所有未被执行的消息
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //方式一：
//        handler.removeMessages(WHAT_RESET_BACK);//移除指定id的所有的消息
        //方式二：移除所有的未被执行的消息
        handler.removeCallbacksAndMessages(null);
    }

    public void onConnected() {
        if (!isConnected()) {
            Toast.makeText(this, "当前网络没有连接", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "当前网络连接正常，获取数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnected() {
        Toast.makeText(this,"当前网络没有连接",Toast.LENGTH_SHORT).show();

    }

}
