package com.example.p2pdemo.activity

import android.util.Log
import com.example.modulebase.BaseActivity
import com.example.p2pdemo.R
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.activity_gesture.*
import java.util.*
import kotlin.math.log

//手势密码activity
class GestureActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_gesture
    }

    override fun initData() {
        mPIV.updateState(listOf(1,2),false)

        mPLV.setOnPatternChangedListener(object : OnPatternChangeListener{
        override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {

        }

        override fun onClear(view: PatternLockerView) {

        }

        override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
            Log.d("LW","手势${hitIndexList.toString()}")
        }

        override fun onStart(view: PatternLockerView) {

        }

    })
    }
}