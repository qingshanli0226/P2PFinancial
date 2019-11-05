package com.example.administrator.p2pdemo.View

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import com.example.administrator.p2pdemo.R
import kotlinx.android.synthetic.main.activity_guide.*
import java.util.*

class GuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        val ali= AlphaAnimation(0.2f,1f)
        ali.duration=3000
        image_guide.startAnimation(ali)
        val time=Timer()
        var i:Int=0
        time.schedule(object : TimerTask() {
            override fun run() {
                i++
                if (i==2){
                   val intent=Intent(this@GuideActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        },0,2000)


    }



}
