package jni.example.p2pinvest.mvp.view.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import jni.example.base.BaseFragment;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.view.InvestTopBar;

public class InvestFragment extends BaseFragment {

    private ChildInvestAllFragment allFragment;
    private ChildInvestRecommendFragment recommendFragment;
    private ChildInvestHotFragment hotFragment;
    private InvestTopBar investTopBar;
    private ViewPager investPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    public void init(View view) {
        allFragment = new ChildInvestAllFragment();
        recommendFragment = new ChildInvestRecommendFragment();
        hotFragment = new ChildInvestHotFragment();

        investTopBar = (InvestTopBar) view.findViewById(R.id.invest_top_bar);
        investPager = (ViewPager) view.findViewById(R.id.invest_pager);


    }

    @Override
    public void initData() {
        fragments.add(allFragment);
        fragments.add(recommendFragment);
        fragments.add(hotFragment);
        investPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        investPager.setCurrentItem(0);

        investPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        investTopBar.setInvestAll();
                        break;
                    case 1:
                        investTopBar.setInvestRecommend();
                        break;
                    case 2:
                        investTopBar.setInvestHot();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        investTopBar.setListener(new InvestTopBar.MyOnClickListener() {
            @Override
            public void getIndex(int index) {
                switch (index){
                    case 0:
                        investPager.setCurrentItem(0);
                        break;
                    case 1:
                        investPager.setCurrentItem(1);
                        break;
                    case 2:
                        investPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragments.clear();
    }
}
