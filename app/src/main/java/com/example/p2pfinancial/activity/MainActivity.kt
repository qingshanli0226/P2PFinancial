package com.example.p2pfinancial.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.base.BaseActivity
import com.example.p2pfinancial.R
import com.example.p2pfinancial.fragment.MainFragMent
import com.example.p2pfinancial.fragment.InvestFragMent
import com.example.p2pfinancial.fragment.MyInvestFragMent
import com.example.p2pfinancial.fragment.MoreFragMent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var fragList = listOf(MainFragMent(), InvestFragMent(), MyInvestFragMent(), MoreFragMent())
    var currentFragment: Fragment? = null
    override fun setLayout(@LayoutRes layout: Int): Int {
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
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (currentFragment != null) {
            beginTransaction.hide(currentFragment!!)
        }
        if (fragment.isAdded) {
            beginTransaction.show(fragment)
        } else {
            beginTransaction.add(R.id.frag, fragment)
        }
        beginTransaction.commit()
        currentFragment = fragment
    }
}
