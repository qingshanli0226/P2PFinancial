package jni.example.p2pinvest.mvp.view.activity;

import android.content.Intent;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import jni.example.base.BaseActivity;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.Product;
import jni.example.p2pinvest.manager.LoginManager;
import jni.example.p2pinvest.mvp.view.fragment.AssetFragment;
import jni.example.p2pinvest.mvp.view.fragment.HomeFragment;
import jni.example.p2pinvest.mvp.view.fragment.InvestFragment;
import jni.example.p2pinvest.mvp.view.fragment.MoreFragment;
import jni.example.p2pinvest.view.BottomBar;
import jni.example.p2pinvest.view.TopBar;

public class MainActivity extends BaseActivity{

    //TODO 当前要展示的Fragment
    private Fragment currentFragment;
    //TODO 存放Fragment的集合
    ArrayList<Fragment> list = new ArrayList();
    //TODO 底部导航
    private BottomBar main_myView;
    //TODO 顶部Bar
    private TopBar bar;

    private LoginManager loginManager;
    //TODO 返回当前子Activity的布局Id
    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }


    //TODO 初始化控件
    @Override
    public void init() {
        main_myView = findViewById(R.id.main_myview);
        bar = findViewById(R.id.top_bar);
        loginManager = LoginManager.getLoginManager(this);
    }

    //TODO 根据传入的值选择Fragment
    private void switchFragment(int tabIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment fragment = list.get(tabIndex);


        //TODO 判断当前显示的fragment和要切换的Fragment是否是一个，如果不是的话
        if (currentFragment != fragment) {
            transaction.hide(currentFragment); //肯定不为空，因为之前已经设置了默认值

            //TODO 如果要切换的fragment没有添加到系统中，首先添加到系统中
            if (!fragment.isAdded()) {
                transaction.add(R.id.frame, fragment).commit();
            } else {
                //TODO 如果之前已经提交过，则直接显示
                transaction.show(fragment).commit();
            }
            currentFragment = fragment;
        }
    }

    //TODO 初始化数据
    @Override
    public void initData() {
        list.add(new HomeFragment());
        list.add(new InvestFragment());
        list.add(new AssetFragment());
        list.add(new MoreFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.frame,list.get(0)).commit();
        currentFragment = list.get(0);
        main_myView.setListener(new BottomBar.TabOnClickListener() {
            @Override
            public void getIndex(int index) {
                if(index==2){
                    if(!loginManager.isLogin()){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        return;
                    }
                }
                switchFragment(index);
                bar.setTopBarText(index);
            }
        });
    }


    public interface onLoginListener{
        void onNoticeLogin();
    }
}
