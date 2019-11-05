package com.example.p2pfinancial.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyFragAdapter(
    fragmentManager: FragmentManager,
    var fragList: List<Fragment>,
    var titles: List<String>?
) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getCount(): Int {
        return fragList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (titles != null) {
            return titles!![position]
        }
        return super.getPageTitle(position)
    }
}