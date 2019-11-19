package com.example.p2pfinancial.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.net.Constant
import com.example.net.Constant.GestureErrorCount
import com.example.p2pfinancial.ACache
import com.example.p2pfinancial.R
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import com.google.gson.Gson
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
                    val get = ACache.get(this@GestureActivity)
                    get.put("isGesture", true)
                    val gson = Gson()
                    val toJson = gson.toJson(Constant.Gesturelist)
                    get.put("gesture", toJson)
                    finish()
                }
                if (GestureErrorCount == 0) {
                    Toast.makeText(this@GestureActivity, "机会用尽请重新绘制", Toast.LENGTH_SHORT).show()
                    Constant.Gesturelist.clear()
                    Constant.GestureErrorCount = 5
                    piv_indicator_gesture.updateState(null, false)
                }
            }

            @SuppressLint("CommitPrefEdits")
            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                if (hitIndexList.size < 4) {
                    tv_gesture_txt.text = "至少绘制4个"
                    return
                }
                val get = ACache.get(this@GestureActivity)
                val asString = get.getAsString("gesture")
                val toJson = Gson().toJson(hitIndexList)
                if (asString.equals(toJson)){
                    tv_gesture_txt.text = "验证成功"
                    Toast.makeText(this@GestureActivity, "验证成功", Toast.LENGTH_SHORT).show()
                    finish()
                }
                if (flag == 1) {
                    gesturelist.addAll(hitIndexList)
                } else {
                    if (gesturelist.size != hitIndexList.size) {
                        tv_gesture_txt.text = "绘制错误,还有" + --GestureErrorCount + "次机会"

                        plv_lock_gesture.updateStatus(false)
                        return
                    }
                    for (i in gesturelist.indices) {
                        if (gesturelist[i] != hitIndexList[i]) {
                            tv_gesture_txt.text = "绘制错误,还有" + --GestureErrorCount + "次机会"
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
