package com.example.month6.view.activiry;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.month6.R;
import com.example.month6.view.mainactivity_frag.HomeFrag;
import com.example.month6.view.mainactivity_frag.MoneyFrag;
import com.example.month6.view.mainactivity_frag.MoreFrag;
import com.example.month6.view.mainactivity_frag.ShowFrag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    ArrayList<Fragment> list = new ArrayList<>();

    HomeFrag f1 = new HomeFrag(this);
    ShowFrag f2 = new ShowFrag(this);
    MoneyFrag f3 = new MoneyFrag(this);
    MoreFrag f4 = new MoreFrag(this);

    @Override
    protected void initData() {
        //底部监听
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioBut1:
                    showFragment(f1);
                    break;
                case R.id.radioBut2:
                    showFragment(f2);
                    break;
                case R.id.radioBut3:
                    showFragment(f3);
                    break;
                case R.id.radioBut4:
                    showFragment(f4);
                    break;
            }
        });
        showFragment(f1);
    }

    @Override
    protected void initView() {

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
