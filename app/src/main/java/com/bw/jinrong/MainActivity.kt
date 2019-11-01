package com.bw.jinrong

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bw.jinrong.controller.fragment.HomeFragment
import kotlinx.android.synthetic.main.main_bottom.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSelect(HomeFragment())
        initView()

    }

    private fun initView() {
        var fragment:Fragment = Fragment()
        ll_main_home.setOnClickListener {
            fragment = HomeFragment()
        }
        setSelect(fragment)
    }

    private fun setSelect(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_main,fragment)
    }
}
