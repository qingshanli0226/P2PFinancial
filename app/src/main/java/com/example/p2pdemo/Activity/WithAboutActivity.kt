package com.example.p2pdemo.Activity

import com.example.base.BaseActivity
import com.example.p2pdemo.CustomView.MyTitleBar
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.activity_withabout.*

class WithAboutActivity :BaseActivity() {

    override fun InitView() {
      setContentView(R.layout.activity_withabout)
    }

    override fun InitData() {
        WithTitleBar.setTitleName(resources.getString(R.string.WithAbout))
        WithTitleBar.setImgLeftShow(R.drawable.left)
        WithTitleBar.init()
        WithTitleBar.setTitlelistener(object :MyTitleBar.ITitleListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
            }
        })
    }

    override fun InitTitle() {
    }
}