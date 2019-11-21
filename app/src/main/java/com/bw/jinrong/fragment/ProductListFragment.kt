package com.bw.jinrong.fragment


import android.os.Handler
import android.support.v4.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import com.bw.base.BaseFragment
import com.bw.base.IBaseView
import com.bw.common.AppNetConfig
import com.bw.jinrong.R
import com.bw.jinrong.adapter.ProductAdapter
import com.bw.jinrong.bean.HomeBean
import com.bw.jinrong.bean.InvestBean
import com.bw.jinrong.presenter.HomePresenter
import pl.droidsonroids.gif.GifImageView


/**
 * A simple [Fragment] subclass.
 */
class ProductListFragment : BaseFragment(), IBaseView<InvestBean> {
    override fun initView() {

    }

    var mview:View? = null
    var list: ArrayList<InvestBean.DataBean>? = null

    override fun initData() {
        mview = baseView
        var homePresenter:HomePresenter = HomePresenter(AppNetConfig.PRODUCT, InvestBean::class.java)
        homePresenter.attachView(this)
        homePresenter.doHttpRequest()
    }

    override fun setView(): Int {
        return R.layout.fragment_product_list
    }

    override fun onConnected() {
        initData()
    }

    override fun onDisConnected() {
        Toast.makeText(context, "网络连接已断开", Toast.LENGTH_SHORT).show()
    }

    override fun onHttpRequestDataSuccess(requestCode: Int, data: InvestBean) {
        if (requestCode == 100) {

            list = data.data as ArrayList<InvestBean.DataBean>?

            var productAdapter:ProductAdapter = ProductAdapter(context,list)
            var listview: ListView? = mview?.findViewById(R.id.lv_product_list)
            listview?.adapter = productAdapter
        }
    }

    override fun onHttpRequestDataListSuccess(data: List<InvestBean>) {

    }

    override fun onHttpRequestDataFailed(fileMess: String) {
        Handler().postDelayed({
            var ll_linear_product:LinearLayout? = mview?.findViewById(R.id.ll_linear_product)
            ll_linear_product?.visibility = View.GONE
            var home_gif_product:GifImageView? = mview?.findViewById(R.id.home_gif_product)
            home_gif_product?.visibility = View.GONE
            var home_empty_product:GifImageView? = mview?.findViewById(R.id.home_empty_product)
            home_empty_product?.visibility = View.VISIBLE

        },2000)
    }

    override fun showLoading() {
        var home_gif_product:GifImageView? = mview?.findViewById(R.id.home_gif_product)
        home_gif_product?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        Handler().postDelayed({
            var home_gif_product:GifImageView? = mview?.findViewById(R.id.home_gif_product)
            home_gif_product?.visibility = View.GONE
            var ll_linear_product:LinearLayout? = mview?.findViewById(R.id.ll_linear_product)
            ll_linear_product?.visibility = View.VISIBLE

        },3000)
    }
}
