package com.example.p2pdemo.activity




import android.text.TextUtils
import android.view.TextureView
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.example.modulecommon.manager.AppManager
import com.example.modulebase.BaseActivity
import com.example.modulebase.BasePresenter
import com.example.modulebase.IBasePresenter
import com.example.modulebase.IBaseView
import com.example.modulecommon.AppNetConfig
import com.example.p2pdemo.R
import com.example.p2pdemo.bean.RegisterBean
import com.example.p2pdemo.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_user_regist.*
import kotlinx.android.synthetic.main.common_title.*


class UserRegistActivity : BaseActivity(),IBaseView<RegisterBean>{
    var registerPresenter : IBasePresenter<RegisterBean>? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_user_regist
    }

    override fun initTitle() {
        tv_title.text = getString(R.string.register1)
        iv_title_setting.visibility = View.INVISIBLE
        iv_title_black.setOnClickListener {
            AppManager.getInstance().removeCurrrent()
        }
    }

    override fun initData() {
        //注册按钮的点击事件
        register_btn.setOnClickListener {
            var userPhone = register_et_number.text.toString().trim()
            var userName = register_et_name.text.toString().trim()
            var userPass = register_et_password.text.toString().trim()
            var confirmPass = register_et_confirmPass.text.toString().trim()
        if (TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPass) || TextUtils.isEmpty(confirmPass))
            Toast.makeText(this,"注册信息不能为空",Toast.LENGTH_SHORT).show()
            else if(!userPass.equals(confirmPass)){
            Toast.makeText(this,"两次填写的密码不一致",Toast.LENGTH_SHORT).show()
            register_et_password.setText("")
            register_et_confirmPass.setText("")
        }else{
            registerPresenter = RegisterPresenter(userName,userPass,userPhone) as IBasePresenter<RegisterBean>
            registerPresenter!!.attachView(this)
            registerPresenter!!.doHttpPostRequest(AppNetConfig.REGISTER_CODE)
         }
        }
    }

    override fun onLoadDataSuccess(requestCode: Int, data: RegisterBean?) {
    }

    override fun onLoadDataListSuccess(requestCode: Int, data: MutableList<RegisterBean>?) {
    }

    override fun onLoadDataPostSuccess(requestCode: Int, data: RegisterBean?) {
        if (requestCode == AppNetConfig.REGISTER_CODE)
        {
            if (data!=null){
                if (data.isIsExist){
                Toast.makeText(this,"注册成功!",Toast.LENGTH_SHORT).show()
                    AppManager.getInstance().removeAll()
                    goToActivity(LoginActivity::class.java,null)
                }else
                    Toast.makeText(this,"此用户已注册!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showLoading(requestCode: Int) {
    }

    override fun hideLoading(requestCode: Int) {
    }
}