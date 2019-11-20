package com.example.p2pdemo.Activity

import com.example.base.BaseActivity
import com.example.p2pdemo.CacheService
import com.example.p2pdemo.R
import com.example.p2pdemo.UpdateApkManager

class WeclomeActivity : BaseActivity(),UpdateApkManager.UpdateManagerListener {
    override fun getUpdateApkInfo(updateBean: CacheService?) {

    }


    override fun InitView() {
        setContentView(R.layout.activity_weclome)
    }

    override fun InitData() {
    }

    override fun InitTitle() {
    }

}