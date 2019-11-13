package com.bwei.p2p;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bwei.base.BaseActivity;
import com.bwei.p2p.fragment.HomeFragment;
import com.bwei.p2p.invest.InvestFragment;
import com.bwei.p2p.fragment.MoreFragment;
import com.bwei.p2p.fragment.UserFragment;

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
                //错误的调用位置
//                homeFragment.show();
                break;
            case 1 :
                if(investFragment == null){
                    investFragment = new InvestFragment();
                    transaction.add(R.id.main_main, investFragment);
                }
                transaction.show(investFragment);

                break;
            case 2 :
                if(meFragment == null){
                    meFragment = new UserFragment();
                    transaction.add(R.id.main_main, meFragment);
                }
                transaction.show(meFragment);

                break;
            case 3 :
                if(moreFragment == null){
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_main, moreFragment);
                }
                transaction.show(moreFragment);

                break;
        }
        transaction.commit();//提交事务

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
    //重写onKeyUp()，实现连续两次点击方可退出当前应用

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

    @Override
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
