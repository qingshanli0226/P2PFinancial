package com.example.p2pdemo.Activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
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
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity:BaseActivity() {
    override fun InitView() {
        setContentView(R.layout.activity_register)
    }

    override fun InitData() {

        Register_TitleBar.setTitleName(resources.getString(R.string.register))
        Register_TitleBar.setImgLeftShow(R.drawable.left)
        Register_TitleBar.init()
    }

    override fun InitTitle() {

        Register_register.setOnClickListener {
            val phone = Register_phone.text.toString().trim()
            val userName = Register_userName.text.toString().trim()
            val pass = Register_pass.text.toString().trim()
            val surePass = Register_surePass.text.toString().trim()

            if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(userName)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(surePass)){
                Toast.makeText(this,"信息不能为空",Toast.LENGTH_SHORT).show()
            }else if(!pass.equals(surePass)){
                Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show()
                Register_pass.setText("")
                Register_surePass.setText("")
            }else{

                val client = AsyncHttpClient()
                val params = RequestParams()
                params.put("name",userName)
                params.put("password",MD5Utils.MD5(pass))
                params.put("phone",phone)

                client.post(AppNetWork.USERREGISTER,params,object :AsyncHttpResponseHandler(){
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseBody: ByteArray?
                    ) {
                        val string = responseBody.toString()
                        val jsonObject = JSONObject(string)
                        val isExist = jsonObject.getBoolean("isExist")
                        if(isExist){
                            Toast.makeText(this@RegisterActivity,"此用户已注册",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@RegisterActivity,"注册成功!",Toast.LENGTH_SHORT).show()

                        }

                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseBody: ByteArray?,
                        error: Throwable?
                    ) {
                        Toast.makeText(this@RegisterActivity,"注册失败!",Toast.LENGTH_SHORT).show()

                    }
                })
            }


        }
    }


}