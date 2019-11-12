package com.example.sixp2p.Fragment.MainFragment

import android.view.View
import androidx.fragment.app.Fragment
import com.example.base.BaseFragment
import com.example.p2pdemo.Adapter.InvestFragmentAdapter
import com.example.p2pdemo.Fragment.InvestFragment.AllMoneyFragment
import com.example.p2pdemo.Fragment.InvestFragment.HotMoneyFragment
import com.example.p2pdemo.Fragment.InvestFragment.RecommendFragment
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.invest_fragment.view.*


class InvestFragment: BaseFragment() {


    override fun inItData() {

        val investTab = baseView!!.invest_tab
        val investVp = baseView!!.invest_VP
        val mlist= mutableListOf<String>()
        mlist.add("全部理财")
        mlist.add("推荐理财")
        mlist.add("热门理财")

        val listFrag= mutableListOf<Fragment>()
        listFrag.add(AllMoneyFragment())
        listFrag.add(RecommendFragment())
        listFrag.add(HotMoneyFragment())


        val adapter= InvestFragmentAdapter(activity!!.supportFragmentManager,listFrag,mlist)
        investVp.adapter=adapter
        investTab.setupWithViewPager(investVp)



        baseView!!.invest_TitleBar.setTitleName("投资")

    }

    override fun onConnected() {
    }

    override fun onDisConnected() {
    }

    override fun setView(): Int {
        return R.layout.invest_fragment
    }


}