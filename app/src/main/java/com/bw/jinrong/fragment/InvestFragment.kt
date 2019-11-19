package com.bw.jinrong.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import com.bw.base.BaseFragment
import com.bw.base.IBaseView
import com.bw.common.AppNetConfig
import com.bw.common.UIUtils
import com.bw.jinrong.R
import com.bw.jinrong.bean.InvestBean
import com.bw.jinrong.presenter.HomePresenter
import com.bw.view.TabPageIndicator

import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class InvestFragment : BaseFragment(), IBaseView<InvestBean> {

    var mView: View? = null

    var fragmentList:MutableList<Fragment> = ArrayList()

    override fun initData() {
        initFragments()

        var adapter = MyAdapter(childFragmentManager,fragmentList)
        //mView = getBaseView();
        val vp_invest = mView?.findViewById<ViewPager>(R.id.vp_invest)
        vp_invest?.adapter = adapter

        val tabpage_invest = mView?.findViewById<TabPageIndicator>(R.id.tabpage_invest)
        tabpage_invest?.setViewPage(vp_invest)

        initTitle()

        val homePresenter = HomePresenter(AppNetConfig().PRODUCT, InvestBean::class.java)
//        homePresenter.attachView(this)
        homePresenter.doHttpRequest()

    }

    private fun initFragments() {
        val productListFragment = ProductListFragment()
        val productRecommondFragment = ProductRecommondFragment()
        val productHotFragment = ProductHotFragment()

        fragmentList.add(productListFragment)
        fragmentList.add(productRecommondFragment)
        fragmentList.add(productHotFragment)

    }

    private fun initTitle() {
        val iv_title_back = mView?.findViewById<ImageView>(R.id.iv_title_back)
        val tv_title = mView?.findViewById<TextView>(R.id.tv_title)
        val iv_title_setting = mView?.findViewById<ImageView>(R.id.iv_title_setting)

        iv_title_back?.visibility = View.GONE
        tv_title?.text = "投资"
        iv_title_setting?.visibility = View.GONE

    }

    override fun setView(): Int {
        return R.layout.fragment_invest
    }

    override fun onConnected() {

    }

    override fun onDisConnected() {

    }

    override fun onHttpRequestDataSuccess(requestCode: Int, data: InvestBean) {

    }

    override fun onHttpRequestDataListSuccess(data: List<InvestBean>) {

    }

    override fun onHttpRequestDataFailed(fileMess: String) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    class MyAdapter(fm: FragmentManager,fragmentList:MutableList<Fragment>) : FragmentPagerAdapter(fm) {

        var fragmentList:MutableList<Fragment> = fragmentList

        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }

        override fun getCount(): Int {
            return if (fragmentList == null){
                0
            }else {
                fragmentList.size
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return UIUtils.getStringArr(R.array.invest_tab)[position]
        }

    }
}
