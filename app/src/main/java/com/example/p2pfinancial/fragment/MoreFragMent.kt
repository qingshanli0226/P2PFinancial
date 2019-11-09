package com.example.p2pfinancial.fragment

import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R

class MoreFragMent : BaseFragment() {


    lateinit var titleBar: TitleBar
    lateinit var mRegist: TextView
    lateinit var mSecret: TextView
    lateinit var mReset: TextView
    lateinit var mContact: TextView
    lateinit var mSms: TextView
    lateinit var mShare: TextView
    lateinit var mAbout: TextView
    lateinit var mSwitch: Switch

    //设置布局
    override fun setLayoutRes(): Int {
        return R.layout.more_fragment;
    }
    //初始化控件
    override fun initView(view: View?) {
        titleBar = view!!.findViewById(R.id.title_more)
        mRegist = view.findViewById(R.id.tv_more_regist)
        mSecret = view.findViewById(R.id.tv_more_secret)
        mReset = view.findViewById(R.id.tv_more_reset)
        mContact = view.findViewById(R.id.tv_more_contact)
        mSms = view.findViewById(R.id.tv_more_sms)
        mShare = view.findViewById(R.id.tv_more_share)
        mAbout = view.findViewById(R.id.tv_more_about)
        mSwitch = view.findViewById(R.id.switch_secret)
    }

    override fun initData() {
        super.initData()
        titleBar.setTitleText("更多")
    }

}