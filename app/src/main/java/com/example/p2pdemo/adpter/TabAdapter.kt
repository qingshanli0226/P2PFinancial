package com.example.p2pdemo.adpter

import com.flyco.tablayout.listener.CustomTabEntity

class TabAdapter(var icon:Int,var unicon:Int,var title:String) : CustomTabEntity{
    override fun getTabUnselectedIcon(): Int {
        return unicon
    }

    override fun getTabSelectedIcon(): Int {
        return icon
    }

    override fun getTabTitle(): String {
        return title
    }
}