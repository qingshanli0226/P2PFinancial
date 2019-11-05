package com.bw.jinrong

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.RadioGroup
import com.bw.jinrong.controller.fragment.HomeFragment
import com.bw.jinrong.controller.fragment.InvestFragment
import com.bw.jinrong.controller.fragment.MeFragment
import com.bw.jinrong.controller.fragment.MoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_bottom.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSelect(HomeFragment())
        initView()

    }

    private fun initView() {
        var fragment:Fragment = Fragment()
//        ll_main_home.setOnClickListener {
//            fragment = HomeFragment()
//        }
        rg_main.setOnCheckedChangeListener { p0, p1 ->
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
