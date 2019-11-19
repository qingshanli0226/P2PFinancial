package com.example.p2pfinancial.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.base.BaseFragment
import com.example.base.IBaseView
import com.example.common.*
import com.example.p2pfinancial.CacheManager
import com.example.p2pfinancial.bean.MainBean
import com.example.p2pfinancial.presenter.MainPresenter
import com.example.p2pfinancial.R
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class MainFragMent : BaseFragment(), IBaseView<MainBean>, CacheManager.IDataRecivedListener {

    override fun onDataRecived(mainBean: MainBean?) {

    }

    lateinit var mBanner: Banner
    var imgList = mutableListOf<String>()
    var titlesList = mutableListOf("分享砍学费", "人脉总动员", "想不到你是这样的app", "购物街,爱不单行")
    lateinit var mLayout: LinearLayout
    lateinit var mLoading: LoadingPage
    lateinit var titleBar: TitleBar

    //布局文件
    override fun setLayoutRes(): Int {
        return R.layout.main_fragment
    }

    lateinit var investPresenter: MainPresenter
    //初始化控件
    override fun initView(view: View?) {
        mBanner = view!!.findViewById(R.id.frag1_banner)
        mLayout = view.findViewById(R.id.main_frag)
        titleBar = view.findViewById(R.id.titlebar)
        mLoading = view.findViewById(R.id.main_loading)

    }

    override fun initData() {
        titleBar.setTitleText("首页")
        CacheManager.getInstance().registerListener(this)
        //网络请求数据
        investPresenter = MainPresenter()
        investPresenter.attachView(this)
        investPresenter.getBannerImg(100)
        mLoading.setAddResetListener(object : LoadingPage.addResetListener {
            override fun resetLoading() {
                investPresenter.getBannerImg(100)
            }

        })

        val beanData = CacheManager.getInstance().beanData
        if (beanData != null) {
            mLoading.isSucceed()
            mLayout.visibility=View.VISIBLE
        }

    }

    //加载中
    override fun onLoading() {
        mLayout.visibility = View.INVISIBLE
        mLoading.startLoading(LoadingPage.LOADING_PAGE)
    }

    //停止加载
    @SuppressLint("HandlerLeak")
    override fun onStopLoading() {
        object : Handler() {}.postDelayed({
            mLoading.isSucceed()
            mLayout.visibility = View.VISIBLE
        }, 1500)
    }

    override fun onGetDataSucess(requestCode: Int, data: MainBean?) {
        if (requestCode == 100) {
            val imageArr = data?.imageArr
            imageArr?.forEach {
                imgList.add(it.imaurl)
            }
            mBanner.setImages(imgList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(1500)
                .setBannerTitles(titlesList)
                .setImageLoader(object : ImageLoader() {
                    override fun displayImage(
                        context: Context?,
                        path: Any?,
                        imageView: ImageView?
                    ) {
                        Glide.with(activity!!).load(path as String).into(imageView!!)
                    }
                })
                .setBannerAnimation(Transformer.DepthPage)
                .start()
        }
    }

    override fun onGetDataListSucess(requestCode: Int, data: MutableList<MainBean>?) {

    }

    override fun onGetDataFailed(requestCode: Int, error: P2PError?) {
        println("zjw_ 错误信息 = ${error!!.errorMessage}")
        mLoading.startLoading(LoadingPage.FAILURE_PAGE)

    }

    override fun onDestroy() {
        super.onDestroy()
        MainPresenter().detachView()
        CacheManager.getInstance().unregisterListener(this)
    }
}