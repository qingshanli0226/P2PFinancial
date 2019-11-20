package com.example.p2pdemo.Fragment.InvestFragment

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.base.BaseFragment
import com.example.p2pdemo.R
import com.example.p2pdemo.Utils.DrawbleUtils
import kotlinx.android.synthetic.main.hotmoneyfragment.view.*
import java.util.*

class HotMoneyFragment:BaseFragment() {

    private val datas = arrayOf(
        "新手福利计划",
        "财神道90天计划",
        "硅谷计划",
        "30天理财计划",
        "180天理财计划",
        "月月升",
        "中情局投资商业经营",
        "大学老师购买车辆",
        "屌丝下海经商计划",
        "美人鱼影视拍摄投资",
        "Android培训老师自己周转",
        "养猪场扩大经营",
        "旅游公司扩大规模",
        "摩托罗拉洗钱计划",
        "铁路局回款计划",
        "屌丝迎娶白富美计划"
    )
    override fun setView(): Int {
        return R.layout.hotmoneyfragment
    }
    override fun inItData() {
        val flow = baseView.MyFlow
        for (item in 0 until datas.size){
            val tv = TextView(context)

            //设置属性
            tv.text = datas[item]
            val mp = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            mp.leftMargin = 5
            mp.rightMargin =5
            mp.topMargin = 5
            mp.bottomMargin = 5
            tv.layoutParams = mp//设置边距

            val padding = 5
            tv.setPadding(padding, padding, padding, padding)//设置内边距

            tv.textSize = 25f

            val random = Random()
            val red = random.nextInt(211)
            val green = random.nextInt(211)
            val blue = random.nextInt(211)
            tv.background=DrawbleUtils.getSelector(DrawbleUtils.getDrawble(Color.rgb(red,green,blue),5f),DrawbleUtils.getDrawble(Color.WHITE,5f))
            flow.addView(tv)
        }
    }

    override fun onConnected() {
    }

    override fun onDisConnected() {
    }


}