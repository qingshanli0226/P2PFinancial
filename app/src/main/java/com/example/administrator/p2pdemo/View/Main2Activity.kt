package com.example.administrator.p2pdemo.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.p2pdemo.R

import java.util.Timer
import java.util.TimerTask

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {

            }
        }, 0, 1000)
    }
}
