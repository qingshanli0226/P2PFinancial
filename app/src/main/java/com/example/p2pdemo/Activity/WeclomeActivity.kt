package com.example.p2pdemo.Activity

import android.view.Window
import android.view.WindowManager
import com.example.base.BaseActivity
import com.example.p2pdemo.R

class WeclomeActivity : BaseActivity() {


    override fun InitView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_weclome)
    }

    override fun InitData() {
    }

    override fun InitTitle() {
    }

}