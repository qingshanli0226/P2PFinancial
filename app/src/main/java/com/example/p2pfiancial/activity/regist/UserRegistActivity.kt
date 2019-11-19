package com.example.p2pfiancial.activity.regist

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.p2pfiancial.R
import com.example.p2pfiancial.util.UIUtils
import kotlinx.android.synthetic.main.activity_user_regist.*
import org.jetbrains.anko.find

/**
 * 注册页面
 */
class UserRegistActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_user_regist

    override fun initView() {
        val mIvTitleBack = this.find<ImageView>(R.id.iv_title_back)
        val mTvTitle = this.find<TextView>(R.id.tv_title)
        val mIvTitleSetting = this.find<ImageView>(R.id.iv_title_setting)
        //隐藏设置
        mIvTitleSetting.visibility = View.GONE
        mTvTitle.text = getString(R.string.app_activity_regist_tv_top_text) //"用户注册"字样
        mIvTitleBack.setOnClickListener { finish() } //返回按钮

        //注册监听
        mBtnRegister.setOnClickListener {
            val name = mEtRegisterName.text.toString().trim()
            val number = mEtRegisterNumber.text.toString().trim()
            val pwd = mEtRegisterPwd.text.toString().trim()
            val pwdAgain = mEtRegisterPwdAgain.text.toString().trim()

            //填写信息不能为空
            if (name.isNullOrBlank() || number.isNullOrBlank() || pwd.isNullOrBlank() || pwdAgain.isNullOrBlank()) {
                UIUtils.toast(getString(R.string.app_activity_regist_message_not_null), false)
            } else if (pwd != pwdAgain) { //两次密码不一致
                UIUtils.toast("两次填写的密码不一致", false)
                mEtRegisterPwd.setText("")
                mEtRegisterPwdAgain.setText("")
            } else {
                //进行联网注册




            }


        }


    }

    override fun initData() {
    }


    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, UserRegistActivity::class.java)
            context.startActivity(intent)
        }
    }
}
