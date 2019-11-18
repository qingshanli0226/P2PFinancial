package com.bwei.p2p

import android.view.View
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.bwei.base.BaseActivity
import com.bwei.base.bean.User
import com.bwei.net.AppNetConfig
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_title.*

class LoginActivity : BaseActivity() {
    lateinit var user:String
    lateinit var pass:String
    override fun onDisConnected() {
        Toast.makeText(this,"网络连接失败",Toast.LENGTH_SHORT).show()
    }

    override fun onConnected() {
        Toast.makeText(this,"网络连接成功",Toast.LENGTH_SHORT).show()
    }


    override fun initView() {
        user=user_name.text.toString().trim()
        pass=user_pass.text.toString().trim()

         }

    override fun initDate() {
        iv_title_back.setVisibility(View.VISIBLE)
        tv_title.setText("用户登录")
        iv_title_setting.setVisibility(View.INVISIBLE)
        user_login.setOnClickListener {
            if (!(user.equals("")||pass.equals(""))){
                OkGo.post<String>(AppNetConfig.LOGIN+"phone="+user+"password="+pass)
                        .execute(object :StringCallback(){
                            override fun onSuccess(response: Response<String>?) {
                                val jsonObject:JSONObject  = JSON.parseObject(response!!.body());
                                val boolean = jsonObject.getBoolean("success");
                                if(boolean){
                                    //解析json数据，生成User对象
                                    var data:String  = jsonObject.getString("data");
                                    val user = JSON.parseObject(data, User::class.java)
                                            //保存用户信息
                                            saveUser (user)
                                    finish()
                                }else{
                                    Toast.makeText(this@LoginActivity, "用户名不存在或密码不正确", Toast.LENGTH_SHORT).show();
                                }

                            }

                            override fun onError(response: Response<String>?) {
                                Toast.makeText(this@LoginActivity,"联网失败",Toast.LENGTH_SHORT).show()

                            }

                        })

            }else{
                Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show()
            }
        }

         }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}
