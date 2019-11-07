package com.example.p2pdemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import com.example.modulebase.BaseActivity
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*

class WelcomeActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }



    override fun initData() {
        setAnimator()
    }
    override fun flagFullScreen() {
        //activity全屏显示 隐藏顶部状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    //设置入场动画 并动画结束后跳转
    private fun setAnimator(){
        //0完全透明到1完全不透明
        var alphaAnimation = AlphaAnimation(0.2f,1f)
        //动画时间
        alphaAnimation.duration = 3000

        //动画监听
//        alphaAnimation.setAnimationListener(object : Animation.AnimationListener{
//            override fun onAnimationRepeat(animation: Animation?) {
//                //在动画结束时跳转到主界面并销毁当前页面
//                startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
//                finish()
//            }
//
//            override fun onAnimationStart(animation: Animation?) {
//            }
//
//            override fun onAnimationEnd(animation: Animation?) {
//            }
//
//        })

        //使用时间戳控制跳转主页面并销毁当前页面
        var count = 0
        var timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
             count++
                if (count == 4){
                    startActivity(Intent(this@WelcomeActivity,
                        MainActivity::class.java))
                    finish()
                }
            }
        },0,1000)


        //给需要的View启动动画
    rl_welcome.startAnimation(alphaAnimation)

    }
}
