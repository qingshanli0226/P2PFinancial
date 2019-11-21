package com.example.p2pfiancial.activity.info

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.commen.ActivityInstanceManager
import com.example.p2pfiancial.R
import com.example.p2pfiancial.activity.MainActivity
import com.example.p2pfiancial.userinfo.UserInfoManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.startActivity

class UserInfoActivity : BaseActivity<Any>() {
    private lateinit var mIvTitleBack: ImageView
    private lateinit var mTvTitle: TextView
    private lateinit var mIvTitleSetting: ImageView
    override fun getLayoutId(): Int =R.layout.activity_user_info

    override fun initView() {
        initTitle()
    }

    private fun initTitle() {
        mIvTitleBack = findViewById<ImageView>(R.id.iv_title_back)
        mTvTitle = findViewById<TextView>(R.id.tv_title)
        mIvTitleSetting = findViewById<ImageView>(R.id.iv_title_setting)
        mIvTitleBack.visibility=View.VISIBLE
        mTvTitle.text = getString(R.string.app_activity_user_info_title_top)
        mIvTitleSetting.visibility = View.INVISIBLE

        //返回
        mIvTitleBack.setOnClickListener {
            ActivityInstanceManager.removeActivity(this)
        }
    }

    override fun initData() {
        mBtnUserLogout.setOnClickListener {
            //退出
            UserInfoManager.getInstance().unLogout()
            ActivityInstanceManager.finishAllActivity()
            startActivity<MainActivity>()
//            ActivityInstanceManager.removeActivity(this)
        }

    }
}
