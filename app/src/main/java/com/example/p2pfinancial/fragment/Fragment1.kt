package com.example.p2pfinancial.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.p2pfinancial.R
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class Fragment1 : Fragment() {

    lateinit var frag1_banner: Banner
    var imgList = mutableListOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)
        frag1_banner = view.findViewById(R.id.frag1_banner)

        frag1_banner.setImages(imgList)
            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            .setDelayTime(1000)
            .setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    Glide.with(activity!!).load(path as Int).into(imageView!!)
                }

            })
            .setBannerAnimation(Transformer.DepthPage)
            .start()

        return view
    }
}