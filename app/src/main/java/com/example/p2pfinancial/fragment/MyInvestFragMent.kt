package com.example.p2pfinancial.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R

class MyInvestFragMent : BaseFragment() {

    lateinit var titleBar: TitleBar
    lateinit var iv_user_img: ImageView
    lateinit var tv_login_name: TextView
    lateinit var iv_login_recharge: ImageView
    lateinit var iv_login_withdraw: ImageView
    lateinit var tv_invest_manage: TextView
    lateinit var tv_reward_manage: TextView
    lateinit var tv_money_manage: TextView

    override fun initView(view: View?) {
        titleBar = view!!.findViewById(R.id.title_mine)
        iv_user_img = view.findViewById(R.id.iv_user_img)
        iv_login_recharge = view.findViewById(R.id.iv_login_recharge)
        iv_login_withdraw = view.findViewById(R.id.iv_login_withdraw)
        tv_login_name = view.findViewById(R.id.tv_login_name)
        tv_invest_manage = view.findViewById(R.id.tv_invest_manage)
        tv_reward_manage = view.findViewById(R.id.tv_reward_manage)
        tv_money_manage = view.findViewById(R.id.tv_money_manage)
    }

    override fun initData() {
        titleBar.setTitleText("我的资产")
    }

    override fun setLayoutRes(): Int {
        return R.layout.myinsert_fragment
    }
}