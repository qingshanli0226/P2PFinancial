package com.example.month6.view.activiry;

import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.common.diyviews.baseclass.BaseActivity;
import com.example.month6.R;
import com.example.month6.view.fragments.HomeFrag;
import com.example.month6.view.fragments.MoneyFrag;
import com.example.month6.view.fragments.MoreFrag;
import com.example.month6.view.fragments.ShowFrag;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    ArrayList<Fragment> list = new ArrayList<>();

    HomeFrag homeFrag = new HomeFrag(this);
    ShowFrag showFrag = new ShowFrag(this);
    MoneyFrag moneyFrag = new MoneyFrag(this);
    MoreFrag moreFrag = new MoreFrag(this);

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
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
        return R.layout.activitymain;
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
