package com.example.month6.view.activirys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.example.common.diyviews.baseclass.BaseActivity
import com.example.common.diyviews.singleclass.ActivityManager
import com.example.month6.R
import kotlinx.android.synthetic.main.activity_gesture.*

class GestureActivity : BaseActivity() {
    override fun initData() {

    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        Log.e("xxxx","目前有"+ActivityManager.getInstance().size)
        return R.layout.activity_gesture
    }
}
