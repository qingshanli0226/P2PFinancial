package com.example.p2pdemo.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.modulebase.BaseActivity
import com.example.modulecommon.manager.AppManager
import com.example.p2pdemo.R
import com.wyp.avatarstudio.AvatarStudio
import kotlinx.android.synthetic.main.activity_usersetting.*
import kotlinx.android.synthetic.main.common_title.*

class UserSettingActivity : BaseActivity() {
    lateinit var sp:SharedPreferences
    override fun getLayoutId(): Int {
        return R.layout.activity_usersetting
    }

    override fun initTitle() {
        tv_title.setText(R.string.tab_text3)
        iv_title_setting.visibility = View.INVISIBLE
        iv_title_black.visibility = View.VISIBLE
    }

    override fun initData() {
        sp = getSharedPreferences("user_info",Context.MODE_PRIVATE)
        //更换头像
        setting_tv_change.setOnClickListener {
            AvatarStudio.Builder(this)
                .needCrop(true)
                .dimEnabled(true)
                .setAspect(1,1)
                .setOutput(75,75)
                .setText("相机","相册","取消")
                .setTextColor(Color.BLACK)
                .show {
                   Glide.with(this).load(it).apply(RequestOptions().circleCrop()).into(setting_iv_icon)
                }

        }

        setting_btn_logout.setOnClickListener {
            sp.edit().clear().apply()
            AppManager.getInstance().removeAll()
            goToActivity(MainActivity::class.java,null)

        }
    }



}