package com.example.p2pfinancial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.p2pfinancial.fragment.Fragment1
import com.example.p2pfinancial.fragment.Fragment2
import com.example.p2pfinancial.fragment.Fragment3
import com.example.p2pfinancial.fragment.Fragment4
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragList = listOf(Fragment1(), Fragment2(), Fragment3(), Fragment4())
    var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
