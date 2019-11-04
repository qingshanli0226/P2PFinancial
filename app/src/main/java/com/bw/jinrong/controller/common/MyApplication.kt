package com.bw.jinrong.controller.common

import android.app.Application
import android.content.Context
import android.os.Handler

class MyApplication : Application() {

    companion object{
        //在整个应用执行过程中，需要提供的变量
        //需要使用的上下文对象，application实例

        lateinit var context:Context
        lateinit var handler: Handler
        lateinit var mainThread: Thread
        var mainThreadId: Int = 0
    }

    override fun onCreate() {
        super.onCreate()
    }

}