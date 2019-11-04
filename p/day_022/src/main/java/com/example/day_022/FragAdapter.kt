package com.example.day_022

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragAdapter constructor(manager: FragmentManager,list:MutableList<Fragment>): FragmentPagerAdapter(manager) {
    var mlist=list
    override fun getItem(position: Int): Fragment {
        return mlist.get(position)
    }

    override fun getCount(): Int {
        return mlist.size
    }
}