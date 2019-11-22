package com.bw.jinrong.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bw.base.utils.SpUtils
import com.bw.jinrong.R
import com.bw.jinrong.fragment.HomeFragment
import com.bw.jinrong.fragment.InvestFragment
import com.bw.jinrong.fragment.MeFragment
import com.bw.jinrong.fragment.MoreFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentFragemnt:Fragment ?= null
    var fragmentList:MutableList<Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSelect(HomeFragment())
        initView()

    }

    private fun initView() {
        fragmentList.add(HomeFragment())
        fragmentList.add(InvestFragment())
        fragmentList.add(MeFragment())
        fragmentList.add(MoreFragment())

        rg_main.setOnCheckedChangeListener { _, p1 ->
            when(p1){
                R.id.rb_main_home ->{
                    setSelect(fragmentList[0])
                    rb_main_home.isChecked = true
                }
                R.id.rb_main_invest ->{
                    setSelect(fragmentList[1])
                    rb_main_invest.isChecked = true
                }
                R.id.rb_main_me ->{
                    if (SpUtils.getInstance().getBoolean("isCheck")){
                        setSelect(fragmentList[2])
                        rb_main_me.isChecked = true
                    }else{
                        startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                    }
                }
                R.id.rb_main_more ->{
                    setSelect(fragmentList[3])
                    rb_main_more.isChecked = true
                }
            }
        }
        rb_main_home.isChecked = true
    }

    private fun setSelect(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        if (currentFragemnt != null){
            transaction.hide(currentFragemnt!!)
        }

        if (fragment.isAdded){
            transaction.show(fragment)
        }else{
            transaction.add(R.id.fl_main,fragment)
        }

        transaction.commit()

        currentFragemnt = fragment

        transaction.show(fragment)
    }
}
