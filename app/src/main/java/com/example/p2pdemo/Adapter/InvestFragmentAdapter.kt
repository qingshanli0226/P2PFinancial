package com.example.p2pdemo.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class InvestFragmentAdapter(manager: FragmentManager, mlist: MutableList<Fragment>, mlist1: MutableList<String>):FragmentPagerAdapter(manager) {

    var list=mlist
    var listTab=mlist1
    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTab.get(position)
    }
}