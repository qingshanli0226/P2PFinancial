package com.example.p2pfiancial.activity

import com.example.base.BaseActivity
import com.example.p2pfiancial.R
import com.example.p2pfiancial.bean.InvestProductBean
import com.example.p2pfiancial.common.BottomBar
import com.example.p2pfiancial.fragment.MineFragment
import com.example.p2pfiancial.fragment.homefragment.HomeFragment
import com.example.p2pfiancial.fragment.investfragment.InvestFragment
import com.example.p2pfiancial.fragment.investfragment.ProductListFragment
import com.example.p2pfiancial.fragment.morefragment.MoreFragment
import com.example.p2pfiancial.util.UIUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ProductListFragment.onRequestDataListener {
    private var oldTime: Long = 0
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
    }

    override fun initData() {
        //未选中的图标
        mBottomBar.setUnselectedIcon(
            R.drawable.bottom01,
            R.drawable.bottom03,
            R.drawable.bottom05,
            R.drawable.bottom07
        )
        //选中的图标
        mBottomBar.setSelectedIcon(
            R.drawable.bottom02,
            R.drawable.bottom04,
            R.drawable.bottom06,
            R.drawable.bottom08
        )

        //标题 "首页", "投资", "我的资产", "更多"
        mBottomBar.setTitle(
            getString(R.string.activity_main_home_text),
            getString(R.string.activity_main_invest_text),
            getString(
                R.string.activity_main_me_text
            ),
            getString(R.string.activity_main_more_text)
        )
        mBottomBar.show()

        //fragment
        val fragments =
            mutableListOf(
                HomeFragment(),
                InvestFragment(), MineFragment(),
                MoreFragment()
            )

        //默认显示首页
        UIUtils.switchFragment(this@MainActivity, R.id.fl_main, fragments[0])

        //设置监听事件
        mBottomBar.setOnCtlSelectListener(object : BottomBar.OnCtlTabSelectListener {
            override fun onTabReselect(position: Int) {
                UIUtils.switchFragment(this@MainActivity, R.id.fl_main, fragments[position])
            }

            override fun onTabSelect(position: Int) {
                UIUtils.switchFragment(this@MainActivity, R.id.fl_main, fragments[position])
            }
        })
    }

    var investProductData: InvestProductBean? = null
    //由ProductListFragment 提供的接口
    override fun onRequestDataSuccess(data: InvestProductBean?) {
        if (data != null) {
            this.investProductData = data
        }
    }


    //双击退出
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - oldTime > 2000) {
            UIUtils.toast("再次点击, 退出应用", false)
        } else {
            finish()
        }
        oldTime = currentTime
    }
}