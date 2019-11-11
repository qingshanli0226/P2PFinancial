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

    val TO_MAIN:Int = 1
    val DOWNLOAD_VERSION_SUCCESS:Int = 2
    val DOWNLOAD_APK_FALL:Int = 3
    val DOWNLOA_APK_SUCCESS:Int = 4

    var connect:Boolean? = null
    var startTime:Long? = null

    protected abstract fun installAPK()

    var dialog:ProgressDialog? = null
    var apkFile: File? = null

    protected abstract fun downloadAPK()

    var updateInfo:UpdateInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        //隐藏顶部的状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ButterKnife.bind(this)

//        initView()

        //一般需要异步操作。在onCreate中只是触发子线程获取数据
        initData()

        //把自己加入到缓存的activity中
        ActivityInstanceManager().addActivity(this)

        //提供启动动画
        setAnimation()

        //联网更新应用
        updateAPKFile()

    }

    protected abstract fun getLayoutId(): Int

    //当前版本号
    protected abstract fun getVersion() : String

    //联网
    protected abstract fun updateAPKFile()

    protected abstract fun toMain()

    //启动动画
    protected abstract fun setAnimation()

    protected abstract fun isConnect() : Boolean

    //初始化数据
    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        ActivityInstanceManager().removeActivity(this)
    }

}