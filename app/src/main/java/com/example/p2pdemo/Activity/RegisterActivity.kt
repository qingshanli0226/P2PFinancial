package com.example.p2pdemo.Activity

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.example.base.BaseActivity
import com.example.common.AppNetWork
import com.example.p2pdemo.Utils.MD5Utils
import com.example.p2pdemo.CustomView.MyTitleBar
import com.example.p2pdemo.R
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
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
        Register_TitleBar.setTitlelistener(object : MyTitleBar.ITitleListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
            }
        })
    }

    override fun InitTitle() {

        Register_register.setOnClickListener {
            val phone = Register_phone.text.toString().trim()
            val userName = Edit_userName.text.toString().trim()
            val pass = Edit_pass.text.toString().trim()
            val surePass = Edit_surePass.text.toString().trim()

            if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(userName)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(surePass)){
                Toast.makeText(this,"信息不能为空",Toast.LENGTH_SHORT).show()
            }else if(!pass.equals(surePass)){
                Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show()
                Edit_pass.setText("")
                Edit_surePass.setText("")
            }else{

                val client = AsyncHttpClient()
                val params = RequestParams()
                params.put("name",userName)
                params.put("password", MD5Utils.MD5(pass))
                params.put("phone",phone)

                client.post(AppNetWork.USERREGISTER,params,object : TextHttpResponseHandler(){
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseString: String?
                    ) {
//                        val jsonObject =
                        val jsonObject = JSON.parseObject(responseString)
                        Log.e("##re",jsonObject.toString())
                        val isExist = jsonObject.getBoolean("isExist")
                        if(isExist){
                            Toast.makeText(this@RegisterActivity,"此用户已注册",Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this@RegisterActivity,"注册成功!",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))

                        }

                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseString: String?,
                        throwable: Throwable?
                    ) {
                        Toast.makeText(this@RegisterActivity,"注册失败!",Toast.LENGTH_SHORT).show()

                    }




                })


            }


        }
    }


}