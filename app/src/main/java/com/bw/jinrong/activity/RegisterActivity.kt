package com.bw.jinrong.activity

import android.view.View
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

            var asyncHttpClient = AsyncHttpClient()
            var params = RequestParams()
            params.put("name",register_name)
            params.put("password",register_pwd)
            params.put("phone",regitster_number)
            asyncHttpClient.post(AppNetConfig.UPDATE,params,object : AsyncHttpResponseHandler(){
                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                    if (statusCode == 200) {
                        println("xxx 注册成功")
                    }
                }

                override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {

                }

            })

        }
    }

    override fun initTitle() {

        iv_title_back.visibility = View.VISIBLE
        tv_title.text = "用户注册"

        iv_title_back.setOnClickListener {
            finish()
        }

    }

    override fun initView(): Int {
        return R.layout.activity_register
    }
}
