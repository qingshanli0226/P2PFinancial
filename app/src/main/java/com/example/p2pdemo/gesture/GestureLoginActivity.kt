package com.example.p2pdemo.gesture

import androidx.core.content.ContextCompat
import com.example.modulebase.BaseActivity
import com.example.p2pdemo.R
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.activity_gesture_login.*

class GestureLoginActivity : BaseActivity() {
    var gestureHelper:GestureHelper? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_user_regist
    }

    override fun initData() {
       loginMsg.text = "绘制解锁图案"
        gestureHelper = GestureHelper(this)
        loginPLV.setOnPatternChangedListener(object : OnPatternChangeListener{
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {

            }

            override fun onClear(view: PatternLockerView) {
                finishIfNeeded()
            }

            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                val isError = !isPatternOk(hitIndexList)
                view.updateStatus(isError)
                loginPIV.updateState(hitIndexList,isError)
                updateMsg()
            }

            override fun onStart(view: PatternLockerView) {
            }

        })
    }
    private fun updateMsg() {
        loginMsg.text = gestureHelper!!.message
        loginMsg.setTextColor(if (gestureHelper!!.isOk)
            ContextCompat.getColor(this, R.color.colorPrimary)
        else
            ContextCompat.getColor(this, R.color.colorAccent))
    }
    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        gestureHelper!!.loginVallidata(hitIndexList)
        return gestureHelper!!.isOk
    }


    private fun finishIfNeeded() {
        if (gestureHelper!!.isFinish) {
            finish()
        }
    }
}