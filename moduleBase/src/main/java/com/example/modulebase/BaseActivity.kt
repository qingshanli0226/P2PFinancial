package com.example.modulebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modulecommon.manager.AppManager

import com.example.modulecommon.manager.NetConnetMannager


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
}
