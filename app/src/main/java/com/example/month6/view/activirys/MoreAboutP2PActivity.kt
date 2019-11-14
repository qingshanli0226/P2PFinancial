package com.example.month6.view.activirys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.common.diyviews.baseclass.BaseActivity
import com.example.month6.R
import com.example.month6.view.customviews.OnTopClickListener
import kotlinx.android.synthetic.main.activity_aboutp2p.*

class MoreAboutP2PActivity : BaseActivity() {
    override fun initData() {

    }

    override fun initView() {
        aboutTop.setOnTopClickListener(object : OnTopClickListener{
            override fun onSetButClick(v: View?) {

            }

            override fun onBackButClick(v: View?) {
                onBackPressed()
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_aboutp2p
    }

}
