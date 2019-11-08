package com.example.p2pfinancial.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.base.BaseFragment
import com.example.base.IBaseView
import com.example.common.P2PError
import com.example.common.TitleBar
import com.example.p2pfinancial.presenter.MainPresenter
import com.example.p2pfinancial.R
import com.example.p2pfinancial.bean.MainBean
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class MainFragMent : BaseFragment(), IBaseView<MainBean> {


    @SuppressLint("HandlerLeak")
    override fun onStopLoading() {
        object : Handler() {}.postDelayed({
            val animationDrawable = iv_main_loading.background as AnimationDrawable
            if (animationDrawable.isRunning) {
                animationDrawable.stop()
                main_frag.visibility = View.VISIBLE
                ll_main_loading.visibility = View.GONE
            }
        }, 1500)
    }


    lateinit var frag1_banner: Banner
    var imgList = mutableListOf<String>()
    var titlesList = mutableListOf("分享砍学费", "人脉总动员", "想不到你是这样的app", "购物街,爱不单行")
    lateinit var iv_main_loading: ImageView
    lateinit var main_frag: LinearLayout
    lateinit var ll_main_loading: LinearLayout
    lateinit var titleBar: TitleBar
    override fun initView(view: View?) {
        frag1_banner = view!!.findViewById(R.id.frag1_banner)
        iv_main_loading = view.findViewById(R.id.iv_main_loading)
        main_frag = view.findViewById(R.id.main_frag)
        ll_main_loading = view.findViewById(R.id.ll_main_loading)
        titleBar = view.findViewById(R.id.titlebar)
        val investPresenter = MainPresenter()
        investPresenter.attachView(this)
        investPresenter.getBannerImg(100)
    }

    override fun setLayoutRes(): Int {
        return R.layout.main_fragment
    }

    override fun initData() {
        println("zjw_ initData")
        titleBar.setTitleText("首页")
    }

    override fun onGetDataSucess(requestCode: Int, data: MainBean?) {
        if (requestCode == 100) {
            println("zjw_ onGetDataSucess")
            val imageArr = data?.imageArr
            imageArr?.forEach {
                imgList.add(it.imaurl)
            }
            frag1_banner.setImages(imgList)
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

    override fun onLoading() {
        val animationDrawable = iv_main_loading.background as AnimationDrawable
        if (!animationDrawable.isRunning) {
            animationDrawable.start()
        }
    }

    override fun onGetDataListSucess(requestCode: Int, data: MutableList<MainBean>?) {

    }

    override fun onGetDataFailed(requestCode: Int, error: P2PError?) {
        println("zjw_ 错误信息 = ${error!!.errorMessage}")
    }

    override fun onDestroy() {
        super.onDestroy()
        MainPresenter().detachView()
    }
}