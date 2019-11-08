package com.bw.common

import android.app.Application
import android.content.Context
import android.os.Handler
//import cn.sharesdk.framework.ShareSDK

class MyApplication : Application() {

    companion object{
        //在整个应用执行过程中，需要提供的变量
        //需要使用的上下文对象，application实例

        var context:Context? = null
        var handler: Handler? = null
        var mainThread: Thread? = null
        var mainThreadId: Int = 0
    }

    override fun onCreate() {
        super.onCreate()

        context = this
        handler = Handler()
        //实例化当前Application的线程即为主线程
        mainThread = Thread.currentThread()
        //获取当前线程的id
        mainThreadId = android.os.Process.myTid()

        //设置未捕获异常的处理器
//        ShareSDK.initSDK(this)

    }

}