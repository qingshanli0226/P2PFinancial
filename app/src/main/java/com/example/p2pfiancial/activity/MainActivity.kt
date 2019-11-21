package com.example.p2pfiancial.activity

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.base.BaseActivity
import com.example.base.BaseFragment
import com.example.p2pfiancial.R
import com.example.p2pfiancial.activity.login.UserLoinActivity
import com.example.p2pfiancial.common.BottomBar
import com.example.p2pfiancial.fragment.homefragment.HomeFragment
import com.example.p2pfiancial.fragment.investfragment.InvestFragment
import com.example.p2pfiancial.fragment.minefragment.MineFragment
import com.example.p2pfiancial.fragment.morefragment.MoreFragment
import com.example.p2pfiancial.userinfo.UserInfoManager
import com.example.p2pfiancial.util.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity<Any>() {
    private var oldTime: Long = 0
    lateinit var fragments: MutableList<BaseFragment<out Any>>
    var currentPosition: Int = 0

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
            getString(R.string.activity_main_me_text),
            getString(R.string.activity_main_more_text)
        )
        mBottomBar.show() //显示底部导航栏
        val homeFragment = HomeFragment() //首页
        val investFragment = InvestFragment() //投资
        val mineFragment = MineFragment() //我的资产
        val moreFragment = MoreFragment() //更多
        //fragment

        fragments = mutableListOf(homeFragment, investFragment, mineFragment, moreFragment)

        //默认显示首页
        switchFragment(fragments[0])
        mBottomBar.getCustomBottomBar()!!.currentTab = currentPosition //图标
        //底部导航栏切换监听
        mBottomBar.setOnCtlSelectListener(object : BottomBar.OnCtlTabSelectListener {
            override fun onTabReselect(position: Int) {
                setSwitchFragment(position)
            }

            override fun onTabSelect(position: Int) {
                setSwitchFragment(position)
            }
        })
    }

    fun setSwitchFragment(position: Int) {
        if (position == 2) {
            //配置我的界面
            deployMineFragment(position)
        } else {
            currentPosition = position
            switchFragment(fragments[position])
        }
    }

    /**
     * 配置我的界面
     */
    private fun deployMineFragment(position: Int) {
        //判断用户是否登录
        if (UserInfoManager.getInstance().isLogin) {
            //已登录,展示用户信息
            switchFragment(fragments[position])
        } else {
            mBottomBar.getCustomBottomBar()!!.currentTab = currentPosition
            //未登录, 弹出log进行提示
            doLogin(position)
        }
    }

    /**
     * 未登录, 弹出log进行提示
     */
    private fun doLogin(position: Int) {
        val alertDialog = AlertDialog.Builder(this).setTitle("提示")
            .setMessage("您还没有登录哦!")
            .setCancelable(false) //屏蔽返回键
            .setPositiveButton("确定") { _, _ ->
                //跳转登录界面
                startActivity<UserLoinActivity>()
                //登录状态监听
                UserInfoManager.getInstance()
                    .registerUserInfoStatusListener { isLogin, dataBean ->
                        if (isLogin) {
                            println("login_$isLogin")
                            switchFragment(fragments[position])
                            mBottomBar.getCustomBottomBar()!!.currentTab = position

                        }
                    }
            }.setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
                switchFragment(fragments[currentPosition])
            }.create()
        alertDialog.setCanceledOnTouchOutside(false)//设置弹窗外失去焦点
        alertDialog.show()
    }


    private var currentFragment: Fragment? = null
    /**
     * fragment切换
     */
    fun switchFragment(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        if (!targetFragment.isAdded) {
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
            }
            transaction.add(R.id.fl_main, targetFragment).commitAllowingStateLoss()
        } else {
            transaction.hide(currentFragment!!).show(targetFragment).commitAllowingStateLoss()
        }
        currentFragment = targetFragment
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