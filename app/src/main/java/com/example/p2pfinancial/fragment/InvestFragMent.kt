package com.example.p2pfinancial.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.base.IBaseView
import com.example.common.BottomBar
import com.example.net.Constant
import com.example.p2pfinancial.InvestPresenter
import com.example.p2pfinancial.R
import com.example.p2pfinancial.adapter.MyFragAdapter
import com.example.p2pfinancial.bean.InvestBean
import com.google.android.material.tabs.TabLayout

class InvestFragMent : Fragment(), BottomBar.OnTapListener, IBaseView<InvestBean> {


    var fragList = listOf(AllFragMent(), RecommendFragMent(), HotFragMent())
    var titles = listOf("全部理财", "推荐理财", "热门理财")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.invest_fragment, container, false)
        val tl_invest = view.findViewById<TabLayout>(R.id.tl_invest)
        val vp_invest = view.findViewById<ViewPager>(R.id.vp_invest)

        val investPresenter = InvestPresenter()
        investPresenter.attachView(this)
        investPresenter.setBaseUrl(Constant.INDEX)
        investPresenter.getBannerImg()

        val myFragAdapter = MyFragAdapter(activity!!.supportFragmentManager, fragList, titles)
        vp_invest.adapter = myFragAdapter
        tl_invest.setupWithViewPager(vp_invest)

        return view
    }

    override fun onGetDataSucess(data: InvestBean?) {
        val imageArr = data?.imageArr
        imageArr?.forEach {
            println("zjw_ :${it.imaurl}")
        }
        println("zjw_ : ${data.toString()}")
    }

    override fun onGetDataListSucess(data: MutableList<InvestBean>?) {
        data?.forEach {
            println("zjw_ : ${it.toString()}")
        }
    }

    override fun onGetDataFailed(message: String?) {
        println("zjw_ : $message")
    }

    override fun tapItemClick(i: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        InvestPresenter().detachView()
    }
}