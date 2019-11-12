package com.example.p2pdemo.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.modulebase.BaseActivity
import com.example.p2pdemo.R
import com.example.p2pdemo.adpter.TabAdapter
import com.example.p2pdemo.fragment.HomeFragment
import com.example.p2pdemo.fragment.InvestFragment
import com.example.p2pdemo.fragment.MeFragment
import com.example.p2pdemo.fragment.MoreFragment
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : BaseActivity() {

    var fragments: List<Fragment> =
        mutableListOf(HomeFragment(), InvestFragment(), MeFragment(), MoreFragment())

    private var titles = arrayListOf<String>()
    private var icons = listOf<Int>(
        R.mipmap.home_select,
        R.mipmap.invest_select,
        R.mipmap.mine_select,
        R.mipmap.more_select
    )
    private var unicons = listOf<Int>(
        R.mipmap.home_unselect,
        R.mipmap.inwest_unselect,
        R.mipmap.mine_unselect,
        R.mipmap.more_unselect
    )
    private var tabData = arrayListOf<CustomTabEntity>()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun onConnected() {
        Toast.makeText(this,"网络重新连接",Toast.LENGTH_SHORT).show()
        switchFragment(fragments[0])
    }

    override fun onDisConnected() {
        Toast.makeText(this,"网络连接已断开",Toast.LENGTH_SHORT).show()
    }
    override fun initTab() {
        //判断当前网络是否连接,如果没有连接则提示童虎
        if (!isConnected()){
            Toast.makeText(this,"当前无网络连接,请确保打开数据网络",Toast.LENGTH_SHORT).show()
            return
        }
        titles.add(getString(R.string.tab_text1))
        titles.add(getString(R.string.tab_text2))
        titles.add(getString(R.string.tab_text3))
        titles.add(getString(R.string.tab_text4))
        for (i in titles.indices) {
            tabData.add(TabAdapter(icons[i], unicons[i], titles[i]))
        }
        var tab_main = findViewById<CommonTabLayout>(R.id.tab_main)
        tab_main.setTabData(tabData)
        tab_main.textSelectColor = Color.BLUE
        tab_main.textUnselectColor = Color.parseColor("#8E8E8E")

        switchFragment(fragments[0])
        tab_main.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                when (position) {
                    0 -> {
                        switchFragment(fragments[0])
                    }

                    1 -> {
                        switchFragment(fragments[1])
                    }

                    2 -> {
                        switchFragment(fragments[2])
                    }

                    3 -> {
                        switchFragment(fragments[3])
                    }
                }

            }

            override fun onTabReselect(position: Int) {

            }

        })
    }

    private var currentFragment: Fragment? = Fragment()
    private fun switchFragment(targetFragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (!targetFragment.isAdded) {
            if (currentFragment != null)
                beginTransaction.hide(currentFragment!!)

            beginTransaction.add(R.id.fl_main, targetFragment).commit()
        } else
            beginTransaction.hide(currentFragment!!).show(targetFragment).commit()

        currentFragment = targetFragment
    }


    var count: Long = 0
    //返回键的监听
    override fun onBackPressed() {
        exit()
    }

    //判断如果点击间隙少于2000就退出
    private fun exit() {
        if (System.currentTimeMillis() - count > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show()
            count = System.currentTimeMillis()
        } else {
            com.example.modulebase.AppManager.getInstance().removeAll()
            exitProcess(0)
        }
    }



}
