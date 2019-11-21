package com.bw.jinrong.activity

import com.bw.base.BaseActivity
import com.bw.common.AppNetConfig
import com.bw.jinrong.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.common_title.*

class RegisterActivity : BaseActivity() {

    override fun initData() {
        btn_register.setOnClickListener {
            val regitster_number = et_register_number.text.toString().trim()
            val register_name = et_register_name.text.toString().trim()
            val register_pwd = et_register_pwd.text.toString().trim()
            val register_pwdgain = et_register_pwdagain.text.toString().trim()

        }
    }

    override fun initTitle() {

        tv_title.text = "用户注册"
    }

    override fun initView(): Int {
        return R.layout.activity_register
    }
}
