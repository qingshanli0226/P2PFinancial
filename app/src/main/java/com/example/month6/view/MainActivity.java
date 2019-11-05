package com.example.month6.view;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.month6.view.mainactivity_frag.HomeFrag;
import com.example.month6.view.mainactivity_frag.ShowFrag;
import com.example.month6.view.mainactivity_frag.MoneyFrag;
import com.example.month6.view.mainactivity_frag.MoreFrag;
import com.example.month6.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;
    ArrayList<Fragment> list=new ArrayList<>();

    HomeFrag f1=new HomeFrag(this);
    ShowFrag f2=new ShowFrag();
    MoneyFrag f3=new MoneyFrag();
    MoreFrag f4=new MoreFrag();

    @Override
    protected void initData() {
        radioGroup = findViewById(R.id.radioGroup);
        //底部监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioBut1: showFragment(f1);break;
                    case R.id.radioBut2: showFragment(f2);break;
                    case R.id.radioBut3: showFragment(f3);break;
                    case R.id.radioBut4: showFragment(f4);break;
                }
            }
        });
        showFragment(f1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //加载fragment
    private void showFragment(Fragment frag){
        if (!frag.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,frag).commit();
            list.add(frag);
        }
        for (Fragment i : list){
            getSupportFragmentManager().beginTransaction().hide(i).commit();
        }
        getSupportFragmentManager().beginTransaction().show(frag).commit();
    }
}
