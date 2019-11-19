package com.example.p2pfinancial.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.net.Constant
import com.example.p2pfinancial.R
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.activity_gesture.*

class GestureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture)
        val gesturelist = Constant.Gesturelist
        var flag = 1
        plv_lock_gesture.setOnPatternChangedListener(object : OnPatternChangeListener {
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {

            }

            override fun onClear(view: PatternLockerView) {
                if (flag == 1) {
                    tv_gesture_txt.text = "请再次绘制解锁图案"
                    flag++
                }
                if (tv_gesture_txt.text == "图案密码设置成功") {
                    finish()
                }
            }

            @SuppressLint("CommitPrefEdits")
            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                if (hitIndexList.size < 4) {
                    tv_gesture_txt.text = "至少绘制4个"
                    return
                }
                if (flag == 1) {
                    gesturelist.addAll(hitIndexList)
                } else {
                    if (gesturelist.size != hitIndexList.size) {
                        tv_gesture_txt.text = "与上次绘制不一样,请重新绘制"
                        plv_lock_gesture.updateStatus(false)
                        return
                    }
                    for (i in gesturelist.indices) {
                        if (gesturelist[i] != hitIndexList[i]) {
                            tv_gesture_txt.text = "与上次绘制不一样,请重新绘制"
                            plv_lock_gesture.updateStatus(false)
                            return
                        }
                    }
                }
                tv_gesture_txt.text = "图案密码设置成功"
                piv_indicator_gesture.updateState(hitIndexList, true)
            }

            override fun onStart(view: PatternLockerView) {
                tv_gesture_txt.text = "开始绘制"
            }
        })
    }
}
