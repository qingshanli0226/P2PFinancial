package com.bw.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import butterknife.ButterKnife
import com.bw.common.ActivityInstanceManager
import com.bw.common.UpdateInfo
import java.io.File

abstract class BaseWelcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initView())

        //隐藏顶部的状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ButterKnife.bind(this)

        initView()

        //一般需要异步操作。在onCreate中只是触发子线程获取数据
        initData()

        //把自己加入到缓存的activity中
        ActivityInstanceManager().addActivity(this)

        //提供启动动画
        setAnimation()

    }

    protected abstract fun initView() : Int

    //启动动画
    protected abstract fun setAnimation()

    //初始化数据
    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        ActivityInstanceManager().removeActivity(this)
    }

}