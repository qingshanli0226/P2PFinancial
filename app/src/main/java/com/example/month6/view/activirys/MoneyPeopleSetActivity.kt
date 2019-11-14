package com.example.month6.view.activirys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.common.diyviews.baseclass.BaseActivity
import com.example.month6.R
import com.example.month6.view.customviews.OnTopClickListener
import kotlinx.android.synthetic.main.activity_peopleset.*

class MoneyPeopleSetActivity : BaseActivity() {
    override fun initData() {

    }

    override fun initView() {
        peopleTopView.setOnTopClickListener(object : OnTopClickListener{
            override fun onBackButClick(v: View?) {
                onBackPressed()
            }

            override fun onSetButClick(v: View?) {

            }

        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_peopleset
    }

}
