package com.example.p2pfinancial.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.base.BaseActivity
import com.example.common.NetConnectManager
import com.example.p2pfinancial.R
import com.example.p2pfinancial.fragment.MainFragMent
import com.example.p2pfinancial.fragment.InvestFragMent
import com.example.p2pfinancial.fragment.MyInvestFragMent
import com.example.p2pfinancial.fragment.MoreFragMent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var fragList = listOf(MainFragMent(), InvestFragMent(), MyInvestFragMent(), MoreFragMent())
    var currentFragment: Fragment? = null

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
        initBottomBar()
    }

    private fun initBottomBar() {
        bottombar.setOneCliecked(1)
        getshowFrag(fragList[0])
        bottombar.setOnTapListener {
            when (it) {
                0 -> {
                    getshowFrag(fragList[0])
                }
                1 -> {
                    getshowFrag(fragList[1])
                }
                2 -> {
                    getshowFrag(fragList[2])
                }
                3 -> {
                    getshowFrag(fragList[3])
                }
            }
        }
        bottombar.setTapNum(4)
        bottombar.setTapText(arrayOf("首页", "投资", "我的资金", "更多"))
        bottombar.setTapDrables(
            arrayOf(
                resources.getDrawable(R.drawable.rb_main_selector),
                resources.getDrawable(R.drawable.rb_invest_selector),
                resources.getDrawable(R.drawable.rb_myinvest_selector),
                resources.getDrawable(R.drawable.rb_more_selector)
            )
        )
    }

    fun getshowFrag(fragment: Fragment) {
        //fragment管理类
        val beginTransaction = supportFragmentManager.beginTransaction()
        //如果不为空就隐藏
        if (currentFragment != null) {
            beginTransaction.hide(currentFragment!!)
        }
        //如果传进来的frangment已经被添加过就显示
        if (fragment.isAdded) {
            beginTransaction.show(fragment)
        } else {//否则就添加
            beginTransaction.add(R.id.frag, fragment)
        }
        //提交
        beginTransaction.commit()
        currentFragment = fragment//把传进来的fragment赋给中间值
    }

    override fun onDestroy() {
        super.onDestroy()
        NetConnectManager.getInstance().unregisterNetConnectListener(this)
    }

    override fun onDisConnect() {
        super.onDisConnect()
        Toast.makeText(this, "网络未连接", Toast.LENGTH_SHORT).show()
    }

    override fun onConnect() {
        super.onConnect()
        Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show()

    }


}
