package jni.example.p2pinvest.mvp.view.activity;

import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import jni.example.base.BaseActivity;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.mvp.view.fragment.Fragment_Asset;
import jni.example.p2pinvest.mvp.view.fragment.Fragment_Home;
import jni.example.p2pinvest.mvp.view.fragment.Fragment_Invest;
import jni.example.p2pinvest.mvp.view.fragment.Fragment_more;
import jni.example.p2pinvest.view.Bottom_Bar;
import jni.example.p2pinvest.view.Top_Bar;

public class MainActivity extends BaseActivity{

    //TODO 当前要展示的Fragment
    private Fragment currentFragment;
    //TODO 存放Fragment的集合
    ArrayList<Fragment> list = new ArrayList();
    //TODO 底部导航
    private Bottom_Bar main_myView;
    //TODO 顶部Bar
    private Top_Bar bar;
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
        list.add(new Fragment_Home());
        list.add(new Fragment_Invest());
        list.add(new Fragment_Asset());
        list.add(new Fragment_more());
        getSupportFragmentManager().beginTransaction().add(R.id.frame,list.get(0)).commit();
        currentFragment = list.get(0);
        main_myView.setListener(new Bottom_Bar.TabOnClickListener() {
            @Override
            public void getIndex(int index) {
                switchFragment(index);
                bar.setTopBarText(index);
            }
        });
    }

    @Override
    public void setWindow() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
