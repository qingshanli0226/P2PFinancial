package com.example.p2pfiancial.gesturelock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.p2pfiancial.R
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.activity_gestures_lock.*

class GesturesLockActivity : AppCompatActivity() {
    private var patternHelper: PatternHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestures_lock)

        this.textMsg.text = "设置解锁图案"
        this.patternHelper = PatternHelper(this)


        //为PatternLockerView添加OnPatternChangeListener并处理相应业务逻辑
        patternLockView.setOnPatternChangedListener(object : OnPatternChangeListener {
            /**
             * 开始绘制图案时（即手指按下触碰到绘画区域时）会调用该方法
             */
            override fun onStart(view: PatternLockerView) {
                //开始

            }

            /**
             * 图案绘制改变时（即手指在绘画区域移动时）会调用该方法，请注意只有 @param hitList改变了才会触发此方法
             */
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {

            }

            /**
             * 图案绘制完成时（即手指抬起离开绘画区域时）会调用该方法
             */
            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                val isOk = isPatternOk(hitIndexList)
                view.updateStatus(!isOk) //更改状态
                patternIndicatorView!!.updateState(hitIndexList, !isOk)
                updateMsg()
            }

            /**
             * 已绘制的图案被清除时会调用该方法
             */
            override fun onClear(view: PatternLockerView) {
                finishIfNeeded();
            }
        })
    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        this.patternHelper!!.validateForSetting(hitIndexList)

        return this.patternHelper!!.isOk;
    }

    private fun updateMsg() {
        this.textMsg.text = this.patternHelper!!.message
        this.textMsg.setTextColor(
            if (this.patternHelper!!.isOk)
                ContextCompat.getColor(this,R.color.colorAccent)
            else ContextCompat.getColor(this, R.color.product_red_common)
        )
    }

    private fun finishIfNeeded() {
        if (this.patternHelper!!.isFinish){
            finish()
        }
    }

    companion object{
        fun startAction(context: Context){
            val intent = Intent(context, GesturesLockActivity::class.java)
            context.startActivity(intent)
        }
    }












}
