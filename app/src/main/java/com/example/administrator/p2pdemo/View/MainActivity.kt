package com.example.administrator.p2pdemo.View

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import com.example.administrator.p2pdemo.R
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr=ArrayList<Int>()
        arr.add(R.drawable.ao)
        arr.add(R.drawable.bo)
        arr.add(R.drawable.ao2)


        main_banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        main_banner.setImages(arr)
        main_banner.setImageLoader(gile())
        main_banner.setDelayTime(2000)
        main_banner.start()
    }
    public class gile : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            imageView!!.setImageResource(path as Int)

         }

        public override fun createImageView(context: Context?): ImageView {
            return super.createImageView(context)
        }

    }
}
