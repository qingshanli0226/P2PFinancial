package com.example.common.diyviews.baseclass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class BaseViewPagerAdapert extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<String> titles;
    Context ctx;

    public BaseViewPagerAdapert(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> fragments, ArrayList<String> titles, Context ctx) {
        super(fm, behavior);
        this.fragments = fragments;
        this.titles = titles;
        this.ctx = ctx;
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
        return titles.get(position);
    }
}
