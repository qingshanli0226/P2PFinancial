package com.example.p2pfinancial.activity

import android.annotation.SuppressLint
import android.widget.Toast
import com.example.base.BaseActivity
import com.example.net.Constant
import com.example.net.Constant.GestureErrorCount
import com.example.common.ACache
import com.example.p2pfinancial.R
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_gesture.*

//图案解锁页面
class GestureActivity : BaseActivity() {

    //设置布局
    override fun setLayout(): Int {
        return R.layout.activity_gesture
    }

    //初始化
    override fun initView() {

    }

    //初始化数据
    override fun initData() {
        super.initData()
        //全局的集合,存储密码下标
        val gesturelist = Constant.Gesturelist
        var flag = 1//标记
        plv_lock_gesture.setOnPatternChangedListener(object : OnPatternChangeListener {
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {
                //下标改变时调用
            }

            override fun onClear(view: PatternLockerView) {
                //绘制完毕后清楚图案调用
                if (flag == 1) {//如果标记是1
                    tv_gesture_txt.text = "请再次绘制解锁图案"
                    flag++
                }
                //如果设置成功
                if (tv_gesture_txt.text == "图案密码设置成功") {
                    //把成功状态存储到sp中
                    val get = ACache.get(this@GestureActivity)
                    get.put("isGesture", true)
                    //把集合变成json串,存储到sp中
                    val gson = Gson()
                    val toJson = gson.toJson(Constant.Gesturelist)
                    get.put("gesture", toJson)
                    finish()//关闭页面
                }
                if (GestureErrorCount == 0) {//如果次数等于0,提醒用户机会用尽
                    Toast.makeText(this@GestureActivity, "机会用尽请重新绘制", Toast.LENGTH_SHORT).show()
                    Constant.Gesturelist.clear()//清空集合
                    GestureErrorCount = 5
                    piv_indicator_gesture.updateState(null, false)//更新小密码图案
                }
            }

            @SuppressLint("CommitPrefEdits")
            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                if (hitIndexList.size < 4) {//至少绘制4个图案
                    tv_gesture_txt.text = "至少绘制4个"
                    return
                }
                //从sp中取出之前存的list集合json串
                val get = ACache.get(this@GestureActivity)
                val asString = get.getAsString("gesture")
                val toJson = Gson().toJson(hitIndexList)
                //并且跟本次绘制的比较
                if (asString.equals(toJson)) {
                    tv_gesture_txt.text = "验证成功"
                    Toast.makeText(this@GestureActivity, "验证成功", Toast.LENGTH_SHORT).show()
                    finish()
                }
                //如果标记flag是1就把绘制的图案添加到集合中
                if (flag == 1) {
                    gesturelist.clear()
                    gesturelist.addAll(hitIndexList)
                } else {//如果标记flag不是1
                    if (gesturelist.size != hitIndexList.size) {//比较数组长度
                        tv_gesture_txt.text = "绘制错误,还有" + --GestureErrorCount + "次机会"
                        plv_lock_gesture.updateStatus(false)//如果不一样就提示错误
                        return
                    }
                    for (i in gesturelist.indices) {
                        if (gesturelist[i] != hitIndexList[i]) {//如果每个元素不一样
                            tv_gesture_txt.text = "绘制错误,还有" + --GestureErrorCount + "次机会"
                            plv_lock_gesture.updateStatus(false)//提示错误
                            return
                        }
                    }
                }
                tv_gesture_txt.text = "图案密码设置成功"
                piv_indicator_gesture.updateState(hitIndexList, true)//更新,并提示绘制成功
            }

            override fun onStart(view: PatternLockerView) {
                //开始绘制时调用
                tv_gesture_txt.text = "开始绘制"
            }
        })
    }
}
