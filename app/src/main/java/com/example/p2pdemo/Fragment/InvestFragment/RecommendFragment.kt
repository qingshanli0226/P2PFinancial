package com.example.p2pdemo.Fragment.InvestFragment

import android.view.View
import com.example.base.BaseFragment
import com.example.p2pdemo.Adapter.RecommendAdapter
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.recommendfragment.view.*

class RecommendFragment:BaseFragment() {


    override fun onConnected() {
    }

    override fun onDisConnected() {
    }

    override fun setView(): Int {
     return R.layout.recommendfragment
    }
    override fun inItData() {

        val star = baseView.RecommendStar
        var listData:MutableList<String> = mutableListOf("新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划","天兵k")

        star.setAdapter(RecommendAdapter(context, listData))
        star.setInnerPadding(20, 20, 20, 20)
        star.setRegularity(30, 35)
        star.setGroup(0)
    }

}