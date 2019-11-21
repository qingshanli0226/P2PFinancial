package jni.example.p2pinvest.mvp.view.activity

import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import jni.example.base.BaseActivity

import jni.example.p2pinvest.R
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : BaseActivity() {
    var handler: Handler = object :Handler(){
        override fun handleMessage(msg: Message) {
            if (msg.what==TO_MAIN){
                finish()
                startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            }
        }
    }
    private val TO_MAIN = 1
    private var startTime:Long = 0


    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun init() {
        startTime = System.currentTimeMillis()
        setAnimation()
        toMain()
    }

    override fun initData() {

    }

    override fun setWindow() {
       window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun setAnimation(){
        var alphaAnimation = AlphaAnimation(0f, 1f)//0：完全透明  1：完全不透明
        alphaAnimation.duration = 3000
        alphaAnimation.interpolator = AccelerateInterpolator()

        welcome_layout.startAnimation(alphaAnimation)
    }
    private fun toMain() {
        val currentTime = System.currentTimeMillis()
        var delayTime = 3000 - (currentTime - startTime)
        if (delayTime < 0) {
            delayTime = 0
        }


        handler.sendEmptyMessageDelayed(TO_MAIN, delayTime)
    }

}
