package com.bw.jinrong.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import android.widget.TextView
import com.bw.base.BaseFragment
import com.bw.base.IBaseView
import com.bw.common.AppNetConfig
import com.bw.common.UIUtils
import com.bw.jinrong.R
import com.bw.jinrong.adapter.ProductAdapter
import com.bw.jinrong.bean.InvestBean
import com.bw.jinrong.bean.Product
import com.bw.jinrong.presenter.HomePresenter

import java.util.ArrayList
import java.util.Random

/**
 * A simple [Fragment] subclass.
 */
class ProductListFragment : BaseFragment(), IBaseView<Product> {

    var mview:View? = null
    var list: List<Product>? = null

    override fun initData() {
        mview = baseView
        val homePresenter = HomePresenter(AppNetConfig().PRODUCT, InvestBean::class.java)
        homePresenter.doHttpRequest()
    }

    override fun setView(): Int {
        return R.layout.fragment_product_list
    }

    override fun onConnected() {

    }

    override fun onDisConnected() {

    }

    override fun onHttpRequestDataSuccess(requestCode: Int, data: Product) {
        if (requestCode == 100) {
            list = listOf(data)

            var productAdapter:ProductAdapter = ProductAdapter(list)
            var listview: ListView? = mview?.findViewById(R.id.lv_product_list)
            listview?.adapter = productAdapter
        }
    }

    override fun onHttpRequestDataListSuccess(data: List<Product>) {

    }

    override fun onHttpRequestDataFailed(fileMess: String) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
