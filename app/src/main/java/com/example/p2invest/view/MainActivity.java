package com.example.p2invest.view;

import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentTransaction;

import com.example.base.BaseActivity;
import com.example.p2invest.R;
import com.example.p2invest.fragments.HomeFragment;
import com.example.p2invest.fragments.MoreFragment;
import com.example.p2invest.fragments.TouziFragment;
import com.example.p2invest.fragments.WodeFragment;
import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends BaseActivity {

    private RadioGroup gr;

    @Override
    protected void initListener() {
       // ImmersionBar.with(this).init();

        gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i== R.id.rb1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainff,new HomeFragment()).commit();
                }
                else   if (i==R.id.rb2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainff,new TouziFragment()).commit();
                }
                else    if (i==R.id.rb3){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainff,new WodeFragment()).commit();
                }
                else    if (i==R.id.rb4){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainff,new MoreFragment()).commit();
                }

            }


        });
    }
    @Override
    protected void initdata() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainff,new HomeFragment()).commit();
        initView();
    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }


    private void initView() {
        gr = (RadioGroup) findViewById(R.id.gr);

    }
}
