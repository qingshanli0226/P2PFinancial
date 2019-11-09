package com.example.p2pfinancial.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R

class MyInvestFragMent : BaseFragment() {

    lateinit var titleBar: TitleBar
    lateinit var mUserImg: ImageView
    lateinit var mLoginName: TextView
    lateinit var mRecharge: ImageView
    lateinit var mWithdraw: ImageView
    lateinit var mInvestManage: TextView
    lateinit var mRewardManage: TextView
    lateinit var mMoneyManage: TextView

    override fun setLayoutRes(): Int {
        return R.layout.myinsert_fragment
    }

    //初始化控件
    override fun initView(view: View?) {
        titleBar = view!!.findViewById(R.id.title_mine)
        mUserImg = view.findViewById(R.id.iv_user_img)
        mRecharge = view.findViewById(R.id.iv_login_recharge)
        mWithdraw = view.findViewById(R.id.iv_login_withdraw)
        mLoginName = view.findViewById(R.id.tv_login_name)
        mInvestManage = view.findViewById(R.id.tv_invest_manage)
        mRewardManage = view.findViewById(R.id.tv_reward_manage)
        mMoneyManage = view.findViewById(R.id.tv_money_manage)
    }

    override fun initData() {
        titleBar.setTitleText("我的资产")
    }
}