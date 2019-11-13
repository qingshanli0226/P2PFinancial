package com.example.p2pfinancial.fragment

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R
import com.example.p2pfinancial.activity.*

class MyInvestFragMent : BaseFragment(), View.OnClickListener {


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

        mRecharge.setOnClickListener(this)
        mWithdraw.setOnClickListener(this)
        mInvestManage.setOnClickListener(this)
        mRewardManage.setOnClickListener(this)
        mMoneyManage.setOnClickListener(this)
    }

    override fun initData() {
        titleBar.setTitleText("我的资产")
        titleBar.setRightText("设置")
        titleBar.setRightTextColor(resources.getColor(R.color.colorBlue))
        titleBar.setTitleInterface(object : TitleBar.TitleInterface {
            override fun leftClick() {

            }

            override fun rightClick() {

            }
        })

        val sharedPreferences = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        val boolean = sharedPreferences.getBoolean("isLogin", false)
        if (boolean) {

        } else {
            Toast.makeText(activity!!, "请先登录", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity!!, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        val boolean = sharedPreferences.getBoolean("isLogin", false)
        if (!boolean) {
            val intent = Intent(activity!!, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent()
        when (v!!.id) {
            R.id.iv_login_recharge -> intent.setClass(activity!!, RechargeActivity::class.java)
            R.id.iv_login_withdraw -> intent.setClass(activity!!, WithdrawActivity::class.java)
            R.id.tv_invest_manage -> intent.setClass(activity!!, InvestManageActivity::class.java)
            R.id.tv_reward_manage -> intent.setClass(activity!!, RewardManageActivity::class.java)
            R.id.tv_money_manage -> intent.setClass(activity!!, MoneyManageActivity::class.java)
        }
        startActivity(intent)
    }
}