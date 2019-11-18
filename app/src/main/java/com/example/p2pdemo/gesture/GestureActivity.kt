package com.example.p2pdemo.gesture

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.modulebase.BaseActivity
import com.example.p2pdemo.R
import com.example.p2pdemo.gesture.GestureHelper
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.activity_gesture.*


//手势密码activity
class GestureActivity : BaseActivity() {
    private var gestureHelper:GestureHelper? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_gesture
    }

    override fun initData() {
//        mPLV.linkedLineView = null
//        mPLV.hitCellView = null
        msgText.text = "设置解锁图案"
        gestureHelper = GestureHelper(this)
        mPLV.setOnPatternChangedListener(object : OnPatternChangeListener{

        override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {

        }

        override fun onClear(view: PatternLockerView) {
        if (gestureHelper!!.isFinish)
            finish()
        }

        override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
            var isOk = isPatternOk(hitIndexList)
            view.updateStatus(!isOk)
            mPIV!!.updateState(hitIndexList,!isOk)
            updateMsg()
        }

        override fun onStart(view: PatternLockerView) {

        }

    })
    }

    private fun updateMsg() {
        msgText.text = gestureHelper!!.message
        msgText.setTextColor(if (gestureHelper!!.isOk)
            Color.BLUE
        else
            Color.RED
        )

    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        gestureHelper!!.validateForSetting(hitIndexList)
        return gestureHelper!!.isOk
    }
}