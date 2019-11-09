package com.example.p2pfinancial.fragment

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R
import com.example.p2pfinancial.adapter.MyFragAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.invest_fragment.*

class InvestFragMent : BaseFragment() {


    var fragList = listOf(AllFragMent(), RecommendFragMent(), HotFragMent())
    var titles = listOf("全部理财", "推荐理财", "热门理财")
    lateinit var titlebar: TitleBar
    lateinit var mTabLayout: TabLayout
    lateinit var mViewPager: ViewPager

    override fun setLayoutRes(): Int {
        return R.layout.invest_fragment
    }

    //初始化控件
    override fun initView(view: View?) {
        titlebar = view!!.findViewById(R.id.invest_titlebar)
        mTabLayout = view.findViewById(R.id.tl_invest)
        mViewPager = view.findViewById(R.id.vp_invest)
    }

    override fun initData() {
        super.initData()
        titlebar.setTitleText("投资")
        val myFragAdapter = MyFragAdapter(activity!!.supportFragmentManager, fragList, titles)
        mViewPager.adapter = myFragAdapter
        mTabLayout.setupWithViewPager(vp_invest)
    }
}