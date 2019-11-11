package com.example.administrator.p2pdemotext.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Vpadp  extends FragmentPagerAdapter {
    ArrayList<Fragment> arr;
    Context context;

    public Vpadp(FragmentManager fm, ArrayList<Fragment> arr, Context context) {
        super(fm);
        this.arr = arr;
        this.context = context;
    }

    public Vpadp(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return arr.get(i);
    }

    @Override
    public int getCount() {
        return arr.size();
    }
}
