package com.bw.jinrong.activity

import android.content.Intent
import android.os.Bundle
import com.bw.base.BaseWelcome
import com.bw.jinrong.R
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.bw.jinrong.MyApplication
import com.bw.jinrong.bean.HomeBean
import com.bw.jinrong.bean.UpdateBean
import com.bw.jinrong.cache.CacheManager
import com.bw.jinrong.cache.UpdateApkManager
import com.bw.jinrong.service.CacheService
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : BaseWelcome(),UpdateApkManager.UpdateManagerListener {

    override fun getUpdateApkInfo(updateBean: UpdateBean) {
        println("xxx updateBean: ${updateBean.apkUrl}")
    }

    override fun toMain() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun setAnimation() {
        var alphaAnimation = AlphaAnimation(0f, 1f)//0：完全透明  1：完全不透明
        alphaAnimation.duration = 3000
//        alphaAnimation.interpolator = AccelerateInterpolator()//设置动画的变化率
        rl_welcome.startAnimation(alphaAnimation)

        alphaAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
//                toMain()
                UpdateApkManager.getInstance().isFirstApk()
            }

            override fun onAnimationStart(p0: Animation?) {
                UpdateApkManager.getInstance().init(this@WelcomeActivity)
                UpdateApkManager.getInstance().registerUpdateApkListener(this@WelcomeActivity)
            }

        })

    }

    override fun initView(): Int {
        return R.layout.activity_welcome
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        UpdateApkManager.getInstance().unRegisterUpdateApkListener()
        UpdateApkManager.getInstance().unBindService()
    }

}
