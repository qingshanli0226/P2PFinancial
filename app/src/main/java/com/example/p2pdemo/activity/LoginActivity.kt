package com.example.p2pdemo.activity

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.modulebase.BaseActivity
import com.example.modulebase.IBasePresenter
import com.example.modulebase.IBaseView
import com.example.modulebase.User
import com.example.modulecommon.AppNetConfig
import com.example.modulecommon.manager.AppManager
import com.example.p2pdemo.R
import com.example.p2pdemo.bean.UserBean
import com.example.p2pdemo.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_title.*

class LoginActivity : BaseActivity(), IBaseView<UserBean> {
    var loginPresenter: IBasePresenter<UserBean>? = null

    override fun onLoadDataSuccess(requestCode: Int, data: UserBean?) {
    }

    override fun onLoadDataListSuccess(requestCode: Int, data: MutableList<UserBean>?) {
    }

    override fun onLoadDataPostSuccess(requestCode: Int, data: UserBean?) {
        Log.d("LW----------",data.toString())
        if (data != null) {
            var user = User()
            user.imageurl = data.data.imageurl
            user.name = data.data.name
            user.phone = data.data.phone
            saveUser(user)//保存用户信息
            AppManager.getInstance().removeAll()//销毁所用Activity
            goToActivity(MainActivity::class.java, null)//跳转主Activity
        }

    }

    override fun showLoading(requestCode: Int) {
    }

    override fun hideLoading(requestCode: Int) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initTitle() {
        tv_title.text = getString(R.string.user_login)
        iv_title_black.visibility = View.VISIBLE
        iv_title_setting.visibility = View.INVISIBLE
    }

    override fun initData() {

        login()//登录按钮点击事件
    }

    private fun login() {
        login_btn.setOnClickListener {
            var phone: String = login_et_phone.text.toString().trim()
            var pwd: String = login_et_pwd.text.toString().trim()
            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                loginPresenter = LoginPresenter(phone, pwd) as IBasePresenter<UserBean>
                loginPresenter!!.attachView(this as IBaseView<UserBean>)
                loginPresenter!!.doHttpPostRequest(AppNetConfig.LOGIN_CODE)

            } else {
                Toast.makeText(this@LoginActivity, "用户名不存在或密码不正确", Toast.LENGTH_SHORT).show()
            }


        }
    }

}