package com.example.p2pdemo.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.base.BaseActivity
import com.example.common.AppNetWork
import com.example.common.MD5Utils
import com.example.p2pdemo.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:BaseActivity() {
    var editPhone: EditText?=null
    var editPass:EditText?=null
    override fun InitView() {
        setContentView(R.layout.activity_login)
    }

    override fun InitData() {
        editPhone=findViewById(R.id.Login_phone)
        editPass=findViewById(R.id.Login_pass)
    }

    override fun InitTitle() {


        Login_but.setOnClickListener {
            if(TextUtils.isEmpty(editPass!!.text.toString().trim())&&TextUtils.isEmpty(editPhone!!.text.toString().trim())){
                Toast.makeText(this@LoginActivity,"不能为空",Toast.LENGTH_SHORT).show()
            }else{
                val client = AsyncHttpClient()
                val params = RequestParams()
                params.put("phone",editPhone!!.text.toString().trim())
                params.put("password",MD5Utils.MD5(editPass!!.text.toString().trim()))
                client.post(AppNetWork.LOGIN,params,object : AsyncHttpResponseHandler(){
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseBody: ByteArray?
                    ) {
                        Toast.makeText(this@LoginActivity,"登录成功!",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseBody: ByteArray?,
                        error: Throwable?
                    ) {
                    }
                })
            }
        }

    }

}