package com.example.p2pmonthhomework

import android.widget.RadioGroup
import androidx.fragment.app.FragmentManager
import com.example.base.BaseActivity
import com.example.p2pmonthhomework.fragments.FragmentHome
import com.example.p2pmonthhomework.fragments.FragmentInvestment
import com.example.p2pmonthhomework.fragments.FragmentMore
import com.example.p2pmonthhomework.fragments.FragmentProperty
import kotlinx.android.synthetic.main.main_bottom.*

class MainActivity : BaseActivity() {

    private lateinit var manager: FragmentManager
    lateinit var fragmenthome: FragmentHome
    lateinit var fragmentinvestment: FragmentInvestment
    lateinit var fragmentproperty: FragmentProperty
    lateinit var fragmentmore: FragmentMore

    override fun initData() {
        initFragments()
        initFoot()
        setHome()
    }

    override fun initView() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    private fun initFoot() {
        rg_group.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                when (p1) {
                    R.id.rb_main_home -> setHome()
                    R.id.rb_main_invest -> setInvest()
                    R.id.rb_main_me -> setMe()
                    R.id.rb_main_more -> setMore()
                }
            }
        })
    }


    private fun setMore() {
        setTextBlack()
        rb_main_more.setTextColor(resources.getColor(R.color.home_back_selected))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragmentmore)
        transaction.commit()
    }

    private fun setMe() {
        setTextBlack()
        rb_main_me.setTextColor(resources.getColor(R.color.home_back_selected01))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragmentproperty)
        transaction.commit()
    }

    private fun setInvest() {
        setTextBlack()
        rb_main_invest.setTextColor(resources.getColor(R.color.home_back_selected))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragmentinvestment)
        transaction.commit()
    }

    private fun setHome() {
        setTextBlack()
        rb_main_home.setTextColor(resources.getColor(R.color.home_back_selected))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragmenthome)
        transaction.commit()
    }

    fun setTextBlack() {
        rb_main_home.setTextColor(resources.getColor(R.color.home_back_unselected))
        rb_main_invest.setTextColor(resources.getColor(R.color.home_back_unselected))
        rb_main_me.setTextColor(resources.getColor(R.color.home_back_unselected))
        rb_main_more.setTextColor(resources.getColor(R.color.home_back_unselected))
    }

    private fun initFragments() {
        manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        fragmenthome = FragmentHome()
        fragmentinvestment = FragmentInvestment()
        fragmentproperty = FragmentProperty()
        fragmentmore = FragmentMore()

        transaction.add(R.id.ll_main, fragmenthome)
        transaction.add(R.id.ll_main, fragmentinvestment)
        transaction.add(R.id.ll_main, fragmentproperty)
        transaction.add(R.id.ll_main, fragmentmore)

        transaction.commit()
    }
}