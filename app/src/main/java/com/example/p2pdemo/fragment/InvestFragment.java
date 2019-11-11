package com.example.p2pdemo.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.modulebase.BaseFragment;
import com.example.p2pdemo.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;


//首页
public class InvestFragment extends BaseFragment {
    @BindView(R.id.iv_title_black)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.invest_tab)
    SlidingTabLayout mTab;
    @BindView(R.id.invest_vp)
    ViewPager mVp;
    private final String[] tabTitles = {"全部理财","推荐理财","热门理财"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void initTitle() {
        tvTitle.setText(R.string.tab_text2);
        ivTitleBack.setVisibility(View.INVISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());
        //设置导航栏
        mVp.setAdapter(new MyPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager()));
        mTab.setViewPager(mVp);
        mTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_invest;
    }
    //viewPagerAdapter内部类
    private class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

    }
}
