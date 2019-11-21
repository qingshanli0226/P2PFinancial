package com.example.p2pfiancial.activity.login

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.presenter.IBasePresenter
import com.example.commen.ActivityInstanceManager
import com.example.p2pfiancial.R
import com.example.p2pfiancial.bean.LoginBean
import com.example.p2pfiancial.userinfo.UserInfoManager
import com.example.p2pfiancial.util.UIUtils
import kotlinx.android.synthetic.main.activity_user_login.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

@Suppress("UNCHECKED_CAST")
class UserLoinActivity : BaseActivity<LoginBean>() {
    val CODE_LOGIN_POST = 10011;
    override fun getLayoutId(): Int = R.layout.activity_user_login

    override fun initView() {
        val mIvTitleBack = this.find<ImageView>(R.id.iv_title_back)
        val mTvTitle = this.find<TextView>(R.id.tv_title)
        val mIvTitleSetting = this.find<ImageView>(R.id.iv_title_setting)
        //隐藏设置
        mIvTitleSetting.visibility = View.GONE
        mTvTitle.text = getString(R.string.app_activity_login_tv_top_text) //"用户登录"字样
        mIvTitleBack.setOnClickListener { ActivityInstanceManager.removeActivity(this) }//返回按钮


        //注册监听
        mBtnLogin.setOnClickListener {
            val number = mEtLoginNumber.text.toString().trim()
            val pwd = mEtLoginPwd.text.toString().trim()

            //填写信息不能为空
            if (number.isNullOrBlank() || pwd.isNullOrBlank()) {
                //app_activity_regist_message_not_null
                UIUtils.toast(getString(R.string.app_activity_login_message_not_null), false)
            } else {
                //进行联网校验login
                val hashMap = HashMap<String, String>()
                hashMap.put("phone", number)
                hashMap.put("password", pwd)


                //创建网络连接
                val iBasePresenter = UserLoginPresenter(hashMap) as IBasePresenter<LoginBean>
                iBasePresenter.attachView(this)
                //开始请求
                iBasePresenter.doHttpPostRequest(CODE_LOGIN_POST)
            }
        }
    }

    override fun onHttpRequestDataSuccess(requestCode: Int, data: LoginBean?) {
        if (requestCode == CODE_LOGIN_POST) {
            if (data != null) {
                if (data.isSuccess) {
                    toast("登录成功")

                    //存到用户管理类
                    UserInfoManager.getInstance().saveUserInfo(data.data)

                    ActivityInstanceManager.removeActivity(this)
                } else {
                    toast("用户名不存在或密码不正确")
                }
            }
        }
    }

    override fun initData() {
    }
}