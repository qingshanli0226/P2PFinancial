package com.example.p2pdemo.Activity;

import android.os.Handler;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.base.BaseActivity;
import com.example.p2pdemo.Fragment.MainFragment.HomeFragment;
import com.example.p2pdemo.Fragment.MainFragment.MoreFragment;
import com.example.p2pdemo.R;
import com.example.sixp2p.Fragment.MainFragment.InvestFragment;
import com.example.sixp2p.Fragment.MainFragment.MyAssetsFragment;

import java.util.ArrayList;
import java.util.List;

import me.sugarkawhi.bottomnavigationbar.BottomNavigationBar;
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity;

public class MainActivity extends BaseActivity {

    BottomNavigationBar bar;
    private Fragment cuurentFragment;
    ArrayList<Fragment> fragments=new ArrayList<>();
    @Override
    protected void InitView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void InitData() {


        bar=findViewById(R.id.MyBar);
        fragments.add(new HomeFragment());
        fragments.add(new InvestFragment());
        fragments.add(new MoreFragment());
        fragments.add(new MyAssetsFragment());
        cuurentFragment=fragments.get(0);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.Main_Fl,fragments.get(0));
        transaction.commit();


        List<BottomNavigationEntity> mybarlist=new ArrayList<>();
        mybarlist.add(new BottomNavigationEntity("首页",R.mipmap.bottom01,R.mipmap.bottom02));
        mybarlist.add(new BottomNavigationEntity("投资",R.mipmap.bottom03,R.mipmap.bottom04));
        mybarlist.add(new BottomNavigationEntity("我的资产",R.mipmap.bottom05,R.mipmap.bottom06));
        mybarlist.add(new BottomNavigationEntity("更多",R.mipmap.bottom07,R.mipmap.bottom08));
        bar.setEntities(mybarlist);




    }

    @Override
    protected void InitTitle() {
        bar.setBnbItemSelectListener(new BottomNavigationBar.IBnbItemSelectListener() {
            @Override
            public void onBnbItemSelect(int position) {

                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                Fragment fragment = fragments.get(position);
                if(cuurentFragment!=fragment){
                    transaction.hide(cuurentFragment);
                    if(!fragment.isAdded()){
                        transaction.add(R.id.Main_Fl,fragment).commit();
                    }else{
                        transaction.show(fragment).commit();
                    }
                }
                cuurentFragment=fragment;
            }
        });


    }


}
