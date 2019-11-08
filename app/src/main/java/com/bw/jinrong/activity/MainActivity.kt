package com.bw.jinrong.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bw.jinrong.R
import com.bw.jinrong.fragment.HomeFragment
import com.bw.jinrong.fragment.InvestFragment
import com.bw.jinrong.fragment.MeFragment
import com.bw.jinrong.fragment.MoreFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSelect(HomeFragment())
        initView()

    }

    private fun initView() {
        var fragment:Fragment = Fragment()
//        ll_main_home.setOnClickListener {
//            fragment = HomeFragment()
//        }
        rg_main.setOnCheckedChangeListener { _, p1 ->
            when(p1){
                R.id.rb_main_home ->{
                    fragment = HomeFragment()
                }
                R.id.rb_main_invest ->{
                    fragment = InvestFragment()
                }
                R.id.rb_main_me ->{
                    fragment = MeFragment()
                }
                R.id.rb_main_more ->{
                    fragment = MoreFragment()
                }
            }
            setSelect(fragment)
        }
        rb_main_home.isChecked = true
    }

    private fun setSelect(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main,fragment).commit()
    }
}
