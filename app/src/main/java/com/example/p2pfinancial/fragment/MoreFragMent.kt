package com.example.p2pfinancial.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.base.BaseFragment
import com.example.common.TitleBar
import com.example.p2pfinancial.R
import com.example.p2pfinancial.activity.AboutActivity
import com.example.p2pfinancial.activity.GestureActivity
import com.example.p2pfinancial.activity.RegisterActivity

class MoreFragMent : BaseFragment(), View.OnClickListener {

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

        mRegist.setOnClickListener(this)
        mSecret.setOnClickListener(this)
        mReset.setOnClickListener(this)
        mContact.setOnClickListener(this)
        mSms.setOnClickListener(this)
        mShare.setOnClickListener(this)
        mAbout.setOnClickListener(this)
    }

    lateinit var sharedPreferences: SharedPreferences

    override fun initData() {
        super.initData()
        titleBar.setTitleText("更多")
        sharedPreferences = activity!!.getSharedPreferences("p2pSp", Context.MODE_PRIVATE)

        mSwitch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                val edit = sharedPreferences.edit()
                if (isChecked) {
                    Toast.makeText(activity!!, "开启手势密码", Toast.LENGTH_SHORT).show()
                    edit.putBoolean("isGesture", true)
                } else {
                    Toast.makeText(activity!!, "关闭手势密码", Toast.LENGTH_SHORT).show()
                    edit.putBoolean("isGesture", false)
                }
                edit.apply()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_more_regist -> register()
            R.id.tv_more_secret -> secret()
            R.id.tv_more_reset -> reset()
            R.id.tv_more_contact -> contact()
            R.id.tv_more_sms -> sms()
            R.id.tv_more_share -> share()
            R.id.tv_more_about -> about()
        }
    }

    private fun about() {
        val intent = Intent(activity!!, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun share() {

    }

    private fun sms() {
        val view = LayoutInflater.from(activity!!).inflate(R.layout.cuotom_dialog, null, false)
        val builder = AlertDialog.Builder(activity!!)
        builder.setView(view)
        builder.show()
    }

    private fun contact() {
        val phone = "010-56253825"
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("联系客服")
        builder.setMessage("是否现在联系客服:$phone")
        builder.setNegativeButton("确定", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        builder.setPositiveButton("取消", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        builder.show()
    }

    private fun reset() {
        val isGesture = sharedPreferences.getBoolean("isGesture", false)
        if (isGesture) {

        } else {
            Toast.makeText(activity!!, "手势密码操作已关闭,请开启后重试", Toast.LENGTH_SHORT).show()
        }

    }

    private fun secret() {
        val intent = Intent(context!!, GestureActivity::class.java)
        startActivity(intent)
    }

    private fun register() {
        val intent = Intent(context!!, RegisterActivity::class.java)
        startActivity(intent)
    }

}