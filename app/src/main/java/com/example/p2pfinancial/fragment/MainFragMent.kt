package com.example.p2pfinancial.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.base.BaseFragMent
import com.example.base.IBaseView
import com.example.p2pfinancial.presenter.MainPresenter
import com.example.p2pfinancial.R
import com.example.p2pfinancial.bean.MainBean
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragMent : BaseFragMent(), IBaseView<MainBean> {

    lateinit var frag1_banner: Banner
    var imgList = mutableListOf<String>()

    override fun initView(view: View?) {
        frag1_banner = view!!.findViewById(R.id.frag1_banner)
    }

    override fun setLayoutRes(): Int {
        return R.layout.main_fragment
    }

    override fun initData() {
        val investPresenter = MainPresenter()
        investPresenter.attachView(this)
        investPresenter.getBannerImg(100)
    }

    override fun onGetDataSucess(requestCode: Int, data: MainBean?) {
        if (requestCode == 100) {
            val imageArr = data?.imageArr
            imageArr?.forEach {
                imgList.add(it.imaurl)
            }
            
            frag1_banner.setImages(imgList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(1500)
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

    override fun onGetDataFailed(requestCode: Int, message: String?) {
        println("zjw_ : Failed $message")
    }

    override fun onDestroy() {
        super.onDestroy()
        MainPresenter().detachView()
    }
}