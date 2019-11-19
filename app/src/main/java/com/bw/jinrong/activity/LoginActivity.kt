package com.bw.jinrong.activity

import android.os.Bundle
import com.bw.base.BaseActivity
import com.bw.jinrong.R

class LoginActivity : BaseActivity() {

    override fun initData() {

    }

    override fun initTitle() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
