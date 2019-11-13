package com.example.month6.view.activirys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.month6.R;
import com.example.month6.view.fragments.HomeFragment;
import com.example.month6.view.fragments.MoneyFragment;
import com.example.month6.view.fragments.MoreFragment;
import com.example.month6.view.fragments.ShowFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity{

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    ArrayList<Fragment> list = new ArrayList<>();

    HomeFragment homeFrag = new HomeFragment(this);
    ShowFragment showFrag = new ShowFragment(this);
    MoneyFragment moneyFrag = new MoneyFragment(this);
    MoreFragment moreFrag = new MoreFragment(this);

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //底部监听
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioBut1:
                    showFragment(homeFrag);
                    break;
                case R.id.radioBut2:
                    showFragment(showFrag);
                    break;
                case R.id.radioBut3:
                    showFragment(moneyFrag);
                    break;
                case R.id.radioBut4:
                    showFragment(moreFrag);
                    break;
            }
        });
        showFragment(homeFrag);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //加载fragment
    private void showFragment(Fragment frag) {
        if (!frag.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, frag).commit();
            list.add(frag);
        }
        for (Fragment i : list) {
            getSupportFragmentManager().beginTransaction().hide(i).commit();
        }
        getSupportFragmentManager().beginTransaction().show(frag).commit();
    }

}
