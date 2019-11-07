package com.example.p2pfinancial.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.base.BaseFragMent
import com.example.p2pfinancial.R
import com.example.p2pfinancial.adapter.MyFragAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.invest_fragment.*

class InvestFragMent : BaseFragMent() {


    var fragList = listOf(AllFragMent(), RecommendFragMent(), HotFragMent())
    var titles = listOf("全部理财", "推荐理财", "热门理财")

    override fun initView(view: View?) {
        val tl_invest = view!!.findViewById<TabLayout>(R.id.tl_invest)
        val vp_invest = view!!.findViewById<ViewPager>(R.id.vp_invest)
        val myFragAdapter = MyFragAdapter(activity!!.supportFragmentManager, fragList, titles)
        vp_invest.adapter = myFragAdapter
        tl_invest.setupWithViewPager(vp_invest)
    }

    override fun setLayoutRes(): Int {
        return R.layout.invest_fragment
    }



}