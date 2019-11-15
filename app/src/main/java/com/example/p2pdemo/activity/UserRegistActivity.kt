package com.example.p2pdemo.activity




import android.view.View
import com.example.modulecommon.manager.AppManager
import com.example.modulebase.BaseActivity
import com.example.p2pdemo.R
import kotlinx.android.synthetic.main.common_title.*


class UserRegistActivity : BaseActivity() {
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
}