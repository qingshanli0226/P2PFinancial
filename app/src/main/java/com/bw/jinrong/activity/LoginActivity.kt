package com.bw.jinrong.activity

import android.content.Intent
import android.view.View
import com.bw.base.BaseActivity
import com.bw.base.utils.SpUtils
import com.bw.common.AppNetConfig
import com.bw.jinrong.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_title.*

class LoginActivity : BaseActivity() {

    override fun initData() {
        val login_number = et_login_number.text.toString().trim()
        val login_pwd = et_login_pwd.text.toString().trim()

        var asyncHttpClient = AsyncHttpClient()
        var params = RequestParams()
        params.put("phone",login_number)
        params.put("password",login_pwd)
        asyncHttpClient.post(AppNetConfig.UPDATE,params,object : AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                if (statusCode == 200) {
                    SpUtils.getInstance().cave("isCheck",true)
                    println("xxx 登录成功")
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                SpUtils.getInstance().cave("isCheck",false)
            }

        })

    }

    override fun initTitle() {
        iv_title_back.visibility = View.VISIBLE
        tv_title.text = "用户登录"

        iv_title_back.setOnClickListener {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }

    }

    override fun initView(): Int {
        return R.layout.activity_login
    }

}
