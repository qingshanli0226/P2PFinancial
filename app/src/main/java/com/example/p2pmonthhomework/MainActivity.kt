package com.example.p2pmonthhomework

import android.widget.RadioGroup
import androidx.fragment.app.FragmentManager
import com.example.base.BaseActivity
import com.example.p2pmonthhomework.fragments.Fragmenthome
import com.example.p2pmonthhomework.fragments.Fragmentinvestment
import com.example.p2pmonthhomework.fragments.Fragmentmore
import com.example.p2pmonthhomework.fragments.Fragmentproperty
import kotlinx.android.synthetic.main.main_bottom.*

class MainActivity : BaseActivity() {

    private lateinit var manager: FragmentManager
    lateinit var fragmenthome: Fragmenthome
    lateinit var fragmentinvestment: Fragmentinvestment
    lateinit var fragmentproperty: Fragmentproperty
    lateinit var fragmentmore: Fragmentmore

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

        fragmenthome = Fragmenthome()
        fragmentinvestment = Fragmentinvestment()
        fragmentproperty = Fragmentproperty()
        fragmentmore = Fragmentmore()

        transaction.add(R.id.ll_main, fragmenthome)
        transaction.add(R.id.ll_main, fragmentinvestment)
        transaction.add(R.id.ll_main, fragmentproperty)
        transaction.add(R.id.ll_main, fragmentmore)

        transaction.commit()
    }
}