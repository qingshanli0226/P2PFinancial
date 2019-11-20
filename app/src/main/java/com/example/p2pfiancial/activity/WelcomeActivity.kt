package com.example.p2pfiancial.activity

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Interpolator
import com.example.commen.ActivityInstanceManager
import com.example.p2pfiancial.R
import com.example.p2pfiancial.util.UIUtils
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.startActivity

class WelcomeActivity : Activity() {
    private var startTime: Long = 0

    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                TO_MAIN -> {
                    finish()
                    startActivity<MainActivity>()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //隐藏顶部的状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)


        //将当前的activity添加到ActivityManager中
        ActivityInstanceManager.addActivity(this)
        //提供启动动画
        setAnimation()

        //联网更新应用
        updateApkFile()
    }

    /**
     * 联网更新应用
     */
    private fun updateApkFile() {
        startTime = System.currentTimeMillis()

        //1. 判断手机是否可以联网
        val connect: Boolean = isConnect()

        if (!connect) { //没有网络
            UIUtils.toast(getString(R.string.app_activity_welcome_no_net), false)
            toMain()

        }else{ //有网络
            toMain()
        }
    }

    private fun toMain() {
        val currentTime = System.currentTimeMillis()
        var delayTime = 3000 - (currentTime - startTime)
        if (delayTime < 0) {
            delayTime = 0
        }

        handler.sendEmptyMessageDelayed(TO_MAIN, delayTime)
    }


    /**
     * 提供启动动画
     */
    private fun setAnimation() {
        val alphaAnimation = AlphaAnimation(0.0f, 1.0f) //0: 完全透明, 1:完全不透明
        alphaAnimation.duration = 3000
        alphaAnimation.interpolator = AccelerateInterpolator() as Interpolator? //设置动画的变化率

        //启动动画
        rl_welcome.startAnimation(alphaAnimation) //添加动画
    }

    //判断是否联网
    private fun isConnect(): Boolean {
        var connected = false
        val manager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo != null) {
            connected = networkInfo.isConnected
        }

        return connected
    }

    companion object {
        const val TO_MAIN = 1
        const val DOWNLOAD_VERSION_SUCCESS = 2
        const val DOWNLOAD_APK_FAIL = 3
        const val DOWNLOAD_APK_SUCCESS = 4
    }

    private var oldTime:Long = 0
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - oldTime > 2000) {
            UIUtils.toast(getString(R.string.app_activity_welcome_back_pressed_toast), false)
        }else{
            finish()
        }
        oldTime = currentTime
    }
}
