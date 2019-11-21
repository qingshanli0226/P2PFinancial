package com.example.p2pdemo.gesture

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.modulebase.BaseActivity
import com.example.modulecommon.Constructor
import com.example.modulecommon.manager.AppManager
import com.example.p2pdemo.R
import com.example.p2pdemo.gesture.GestureHelper
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.activity_gesture.*
import kotlinx.android.synthetic.main.activity_gesture_login.*


//手势密码activity
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GestureActivity : BaseActivity() {
    private var gestureHelper: GestureHelper? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_gesture
    }

    override fun initData() {
        gestureHelper = GestureHelper(this)
        var bundle = intent.getBundleExtra(Constructor.BUNDLE)
        var gesture = bundle.getString("gesture")
        if (gesture != null) {
            if (gesture == "setting") {
                settingGesture()//设置
                return
            } else if (gesture == "reset") {
                resetGesture()//重置
            } else if (gesture == "login") {
                loginGesture()//登录
            }
        }
//        mPLV.linkedLineView = null
//        mPLV.hitCellView = null

    }

    private fun loginGesture() {
        msgText.text = "请绘制解锁图案"
        gestureHelper = GestureHelper(this)
        mPLV.setOnPatternChangedListener(object : OnPatternChangeListener{
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {}

            override fun onClear(view: PatternLockerView) {
                if (gestureHelper!!.isFinish)
                    AppManager.getInstance().removeCurrrent()
            }

            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                val isError = !isLoginPatternOk(hitIndexList)
                view.updateStatus(isError)
                mPIV.updateState(hitIndexList,isError)
                updateMsg()
            }

            override fun onStart(view: PatternLockerView) {
            }

        })
    }

    private fun isLoginPatternOk(hitIndexList: List<Int>): Boolean {
        gestureHelper!!.loginVallidata(hitIndexList)
        return gestureHelper!!.isOk
    }

    private fun updateMsg() {
        msgText.text = gestureHelper!!.message
        msgText.setTextColor(
            if (gestureHelper!!.isOk)
                Color.BLUE
            else
                Color.RED
        )

    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        gestureHelper!!.validateForSetting(hitIndexList)
        return gestureHelper!!.isOk
    }
    private fun resetGesture(){
        settingGesture()
    }
    private fun settingGesture() {
        msgText.text = "设置解锁图案"
        mPLV.setOnPatternChangedListener(object : OnPatternChangeListener {
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {}

            override fun onClear(view: PatternLockerView) {
                if (gestureHelper!!.isFinish)
                    AppManager.getInstance().removeCurrrent()
            }

            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                var isOk = isPatternOk(hitIndexList)
                view.updateStatus(!isOk)
                mPIV!!.updateState(hitIndexList, !isOk)
                updateMsg()
            }

            override fun onStart(view: PatternLockerView) {}
        })
    }
}