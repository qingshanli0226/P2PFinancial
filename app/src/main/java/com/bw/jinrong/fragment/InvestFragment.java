package com.bw.jinrong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.base.BaseFragment;
import com.bw.jinrong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends BaseFragment {

    @Override
    protected void initData() {
        initFragments();

        MyAdapter adapter = new MyAdapter(getFragmentManager());
    }

    private List<Fragment> fragmentList = new ArrayList<>();
    private void initFragments() {

    }

    @Override
    protected int setView() {
        return 0;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
