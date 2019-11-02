package com.example.p2pfiancial.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import butterknife.ButterKnife
import com.example.p2pfiancial.R

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //隐藏顶部的状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
        ButterKnife.bind(this)





    }
}
