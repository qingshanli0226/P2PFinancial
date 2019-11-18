package com.example.p2pmonthhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setLayoutAnimation()
    }

    private fun setLayoutAnimation() {
        var alphaAnimation : AlphaAnimation = AlphaAnimation(0.3f,1f)
        alphaAnimation.duration = 2000
        layout_welcome.startAnimation(alphaAnimation)

        alphaAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                IntentThread().start()
            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })
    }

    inner class IntentThread : Thread(){
        override fun run() {
            super.run()
            sleep(1500)
            startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
            this.interrupt()
        }
    }
}
