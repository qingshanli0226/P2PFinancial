package com.bwei.p2p.invest;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment>list;
    public MyViewPagerAdapter(@NonNull FragmentManager fm,List<Fragment>list) {
        super(fm);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
}
