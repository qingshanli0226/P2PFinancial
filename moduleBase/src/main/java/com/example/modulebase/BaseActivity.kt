package com.example.modulebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modulecommon.Constructor
import com.example.modulecommon.manager.AppManager

import com.example.modulecommon.manager.NetConnetMannager
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar


//Activity基类
abstract class BaseActivity : AppCompatActivity(), NetConnetMannager.INetConnectListener {
    override fun onConnected() {

    }

    override fun onDisConnected() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flagFullScreen()
        setContentView(getLayoutId())
        ImmersionBar.with(this).init()
        initTitle()
        initData()
        initTab()
        NetConnetMannager.getInstance().registerNetConnectListener(this)//注册监听网络连接
        AppManager.getInstance().add(this)
    }

    open fun flagFullScreen(){}

    open fun isConnected():Boolean{
       return NetConnetMannager.getInstance().isConnectStatus
    }
    open fun initTab(){}

     open fun initData(){}

     open fun initTitle(){}

    abstract fun  getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getInstance().remove(this)
        NetConnetMannager.getInstance().unRegisterNetConnerctListener(this)
    }
    //读取用户信息
    open fun readUser() : User{
        var sp = this.getSharedPreferences(Constructor.SP_NAME_USERINFO, Context.MODE_PRIVATE)
        var user = User()
        user.name = sp.getString("name", "")
        user.imageurl = sp.getString("imageurl", "")
        user.phone = sp.getString("phone", "")
        user.isCredit = sp.getBoolean("iscredit", false)

        return user
    }

    //启动新的activity
    fun goToActivity(Activity: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, Activity)
        //携带数据
        if (bundle != null && bundle.size() != 0) {
            intent.putExtra(Constructor.BUNDLE, bundle)
        }

        startActivity(intent)
    }
    //保存用户信息
  open  fun saveUser(user: User) {
        var sp = this.getSharedPreferences(Constructor.SP_NAME_USERINFO, Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putString("name", user.name)
        editor.putString("imageurl", user.imageurl)
        editor.putBoolean("iscredit", user.isCredit)
        editor.putString("phone", user.phone)
        editor.apply()//必须提交，否则保存不成功
    }
}
