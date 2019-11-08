package com.example.p2pfinancial.fragment

import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R

class MoreFragMent : BaseFragment() {


    lateinit var titleBar: TitleBar
    lateinit var tv_more_regist: TextView
    lateinit var tv_more_secret: TextView
    lateinit var tv_more_reset: TextView
    lateinit var tv_more_contact: TextView
    lateinit var tv_more_sms: TextView
    lateinit var tv_more_share: TextView
    lateinit var tv_more_about: TextView
    lateinit var switch_secret: Switch

    override fun initView(view: View?) {

        titleBar = view!!.findViewById(R.id.title_more)
        tv_more_regist = view.findViewById(R.id.tv_more_regist)
        tv_more_secret = view.findViewById(R.id.tv_more_secret)
        tv_more_reset = view.findViewById(R.id.tv_more_reset)
        tv_more_contact = view.findViewById(R.id.tv_more_contact)
        tv_more_sms = view.findViewById(R.id.tv_more_sms)
        tv_more_share = view.findViewById(R.id.tv_more_share)
        tv_more_about = view.findViewById(R.id.tv_more_about)
        switch_secret = view.findViewById(R.id.switch_secret)
    }

    override fun initData() {
        super.initData()
        titleBar.setTitleText("更多")
    }

    override fun setLayoutRes(): Int {
        return R.layout.more_fragment;
    }
}