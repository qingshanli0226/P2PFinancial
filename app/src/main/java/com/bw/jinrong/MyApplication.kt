package com.bw.jinrong

import android.app.Application
import android.content.Context
import android.os.Handler
import android.support.multidex.MultiDex
import com.bw.common.NetConnectManager
import com.bw.common.UIUtils
import com.bw.jinrong.cache.CacheManager
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

//import cn.sharesdk.framework.ShareSDK

class MyApplication : Application() {

    companion object{
        //在整个应用执行过程中，需要提供的变量
        //需要使用的上下文对象，application实例

        var context:Context? = null
        var handler: Handler? = null
        var mainThread: Thread? = null
        var mainThreadId: Int = 0

        var refWatcher: RefWatcher? = null
        var myApplication:MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        NetConnectManager.getInstance().init(this)

        if (!LeakCanary.isInAnalyzerProcess(this)){
            refWatcher = LeakCanary.install(this)
        }

        context = this
        handler = Handler()
        //实例化当前Application的线程即为主线程
        mainThread = Thread.currentThread()
        //获取当前线程的id
        mainThreadId = android.os.Process.myTid()

        UIUtils(context, handler, mainThread, mainThreadId)

        CacheManager.getInstance().init(this)

    }


}