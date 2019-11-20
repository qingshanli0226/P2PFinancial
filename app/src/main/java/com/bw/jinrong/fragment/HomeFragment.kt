package com.bw.jinrong.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.os.*
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
import com.bw.jinrong.bean.UpdateBean
import com.bw.jinrong.cache.CacheManager
import com.bw.jinrong.presenter.HomePresenter
import com.bw.jinrong.service.CacheService
import com.bw.view.RoundProgress
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

/**
 * A simple [Fragment] subclass.
 */
@SuppressLint("ValidFragment")
class HomeFragment : BaseFragment(), IBaseView<HomeBean>, CacheManager.homeReceivedListener {
    override fun onHomeDataReceived(bean: HomeBean?) {

    }


    var mView:View? = null

    var roundPro_home: RoundProgress? = null

    var currentProgress: Int = 0

    //图片
    var bannerList = mutableListOf<String>()

    //文字
    var titleList = mutableListOf<String>()

    private val runnable = Runnable {
        roundPro_home?.max = 100
        for (i in 0 until currentProgress) {
            roundPro_home?.progress = i + 1

            SystemClock.sleep(20)
            //强制重绘
            //主线程、分线程都可以如此调用
            roundPro_home?.postInvalidate()
        }
    }

    override fun initData() {

        mView = baseView

        if (!isConnected){
            Toast.makeText(context,"当前网络没有连接",Toast.LENGTH_SHORT).show()
            return
        }

        CacheManager.getInstance().registerListener(this)

        val homeData = CacheManager.getInstance().homeData
        if (homeData != null){
            for (item in 0 until homeData.imageArr.size) {
                val mImaurl = homeData.imageArr[item].imaurl.toString()
                bannerList.add(mImaurl)
                currentProgress = homeData.proInfo.progress.toInt()
            }
        }

        Toast.makeText(context,"网络良好",Toast.LENGTH_SHORT).show()
        //请求数据banner
        val homePresenter = HomePresenter(AppNetConfig.INDEX,HomeBean::class.java)
        homePresenter.attachView(this)
        homePresenter.doHttpRequest()
    }

    override fun setView(): Int {
        return R.layout.fragment_home
    }

    override fun onHttpRequestDataSuccess(requestCode: Int, data: HomeBean) {
//        var oos:ObjectOutputStream? = null
        if (requestCode == 100) {
            bannerList.clear()
            for (item in 0 until data.imageArr.size){
                val mImaurl = data.imageArr[item].imaurl.toString()
                bannerList.add(mImaurl)
                currentProgress = data.proInfo.progress.toInt()

//                try {
//                    oos = ObjectOutputStream(FileOutputStream("sdcard/${data.imageArr[item].id}.txt"))
//                    oos.writeObject(mImaurl)
//                }catch (e:IOException){
//                    e.printStackTrace()
//                }finally {
//                    oos?.close()
//                }

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

//        Toast.makeText(context,Environment.DIRECTORY_DOWNLOADS,Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        Handler().postDelayed({
            val home_gif = mView?.home_gif
            home_gif?.visibility = View.GONE
            val ll_linear_home = view?.ll_linear_home
            ll_linear_home?.visibility = View.VISIBLE

            roundPro_home = view?.roundPro_home
//            currentProgress = 90
            Thread(runnable).start()

            if (bannerList != null){
                titleList.clear()
                titleList.add("1")
                titleList.add("2")
                titleList.add("3")
                titleList.add("4")
                val banner = view?.banner
                banner?.setImages(bannerList)
                    ?.setImageLoader(MyLoader())
                    ?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    ?.setBannerTitles(titleList)
                    ?.setDelayTime(1500)
                    ?.start()
            }

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
        CacheManager.getInstance().unregisterListener(this)
    }
}