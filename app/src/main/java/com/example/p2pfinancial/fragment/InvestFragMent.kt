package com.example.p2pfinancial.fragment

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R
import com.example.p2pfinancial.adapter.MyFragAdapter
import com.google.android.material.tabs.TabLayout

class InvestFragMent : BaseFragment() {


    var fragList = listOf(AllFragMent(), RecommendFragMent(), HotFragMent())
    var titles = listOf("全部理财", "推荐理财", "热门理财")
    lateinit var titlebar: TitleBar
    override fun initView(view: View?) {
        val tl_invest = view!!.findViewById<TabLayout>(R.id.tl_invest)
        val vp_invest = view!!.findViewById<ViewPager>(R.id.vp_invest)
        titlebar = view.findViewById(R.id.invest_titlebar)
        val myFragAdapter = MyFragAdapter(activity!!.supportFragmentManager, fragList, titles)
        vp_invest.adapter = myFragAdapter
        tl_invest.setupWithViewPager(vp_invest)
    }

    override fun initData() {
        super.initData()
        titlebar.setTitleText("投资")
    }

    override fun setLayoutRes(): Int {
        return R.layout.invest_fragment
    }


}