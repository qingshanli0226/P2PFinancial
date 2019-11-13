package com.bw.jinrong.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bw.base.BaseFragment
import com.bw.base.IBaseView
import com.bw.base.LoadingPage
import com.bw.base.utils.P2PError
import com.bw.common.AppNetConfig
import com.bw.jinrong.R
import com.bw.jinrong.bean.HomeBean
import com.bw.jinrong.bean.Index
import com.bw.jinrong.bean.Product
import com.bw.jinrong.presenter.HomePresenter
import com.bw.view.RoundProgress
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
@SuppressLint("ValidFragment")
class HomeFragment : BaseFragment(), IBaseView<HomeBean> {

    var mView:View? = null
    val roundPro_home: RoundProgress? = null

    var currentProgress: Int = 90

    var bannerList = mutableListOf<String>()

    val handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            if (msg?.what == 100){
                for (i in 0 until currentProgress) {
                    roundPro_home?.progress = i + 1

                    SystemClock.sleep(20)
                    //强制重绘
                    //主线程、分线程都可以如此调用
                    roundPro_home?.postInvalidate()
                }
            }
        }
    }

//    private val runnable = Runnable {
//        roundPro_home?.max = 100
//        for (i in 0 until currentProgress) {
//            roundPro_home?.progress = i + 1
//
//            SystemClock.sleep(20)
//            //强制重绘
//            //主线程、分线程都可以如此调用
//            roundPro_home?.postInvalidate()
//        }
//    }

    override fun initData() {

        if (!isConnected){
            Toast.makeText(context,"当前网络没有连接",Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(context,"网络良好",Toast.LENGTH_SHORT).show()
        mView = baseView
        //请求数据banner
        val homePresenter = HomePresenter()
        homePresenter.attachView(this)
        homePresenter.doHttpRequest()
    }

    override fun setView(): Int {
        return R.layout.fragment_home
    }

    override fun onHttpRequestDataSuccess(requestCode: Int, data: HomeBean) {
        if (requestCode == 100) {
            for (item in 0 until data.imageArr.size){
                val mImaurl = data.imageArr[item].imaurl.toString()
                bannerList.add(mImaurl)
            }

            if (bannerList != null){
                var titleList = mutableListOf<String>()
                titleList.add("1")
                titleList.add("2")
                titleList.add("3")
                titleList.add("4")
                val banner = mView?.banner
                banner?.setImages(bannerList)
                    ?.setImageLoader(MyLoader())
                    ?.setBannerTitles(titleList)
                    ?.setDelayTime(1500)
                    ?.start()

//                Thread(runnable).start()
                handler.sendEmptyMessageAtTime(100,1000)
            }

        }
    }

    inner class MyLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context!!).load(path).into(imageView!!)
        }

    }

    override fun onHttpRequestDataListSuccess(data: List<HomeBean>) {

    }

    override fun onHttpRequestDataFailed(fileMess: String) {
        Handler().postDelayed({
            val ll_linear_home = view?.ll_linear_home
            ll_linear_home?.visibility = View.GONE
            val home_gif = view?.home_gif
            home_gif?.visibility = View.GONE
            val home_empty = view?.home_empty
            home_empty?.visibility = View.VISIBLE
        },2000)
    }

    override fun showLoading() {
        val home_gif = mView?.home_gif
        home_gif?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        Handler().postDelayed({
            val home_gif = view?.home_gif
            home_gif?.visibility = View.GONE
            val ll_linear_home = view!!.ll_linear_home
            ll_linear_home.visibility = View.VISIBLE

//            currentProgress = 90

        },3000)
    }

    override fun onConnected() {
        initData()
    }

    override fun onDisConnected() {
        Toast.makeText(context, "网络连接已断开", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        HomePresenter().datachView()
    }
}