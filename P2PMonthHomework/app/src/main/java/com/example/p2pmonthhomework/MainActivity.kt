package com.example.p2pmonthhomework

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.p2pmonthhomework.fragments.Fragment_home1
import com.example.p2pmonthhomework.fragments.Fragment_home2
import com.example.p2pmonthhomework.fragments.Fragment_home3
import com.example.p2pmonthhomework.fragments.Fragment_home4
import kotlinx.android.synthetic.main.main_bottom.*

class MainActivity : AppCompatActivity() {


    private lateinit var manager: FragmentManager
    lateinit var fragment_home1: Fragment_home1
    lateinit var fragment_home2: Fragment_home2
    lateinit var fragment_home3: Fragment_home3
    lateinit var fragment_home4: Fragment_home4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
        initFoot()
        setHome()
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
        transaction.replace(R.id.ll_main, fragment_home4)
        transaction.commit()
    }

    private fun setMe() {
        setTextBlack()
        rb_main_me.setTextColor(resources.getColor(R.color.home_back_selected01))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragment_home3)
        transaction.commit()
    }

    private fun setInvest() {
        setTextBlack()
        rb_main_invest.setTextColor(resources.getColor(R.color.home_back_selected))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragment_home2)
        transaction.commit()
    }

    private fun setHome() {
        setTextBlack()
        rb_main_home.setTextColor(resources.getColor(R.color.home_back_selected))
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.ll_main, fragment_home1)
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

        fragment_home1 = Fragment_home1()
        fragment_home2 = Fragment_home2()
        fragment_home3 = Fragment_home3()
        fragment_home4 = Fragment_home4()

        transaction.add(R.id.ll_main, fragment_home1)
        transaction.add(R.id.ll_main, fragment_home2)
        transaction.add(R.id.ll_main, fragment_home3)
        transaction.add(R.id.ll_main, fragment_home4)

        transaction.commit()
    }
}