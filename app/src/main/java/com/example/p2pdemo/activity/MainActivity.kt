package com.example.p2pdemo.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import butterknife.BindView
import com.example.p2pdemo.R
import com.example.p2pdemo.adpter.TabAdapter
import com.example.p2pdemo.fragment.HomeFragment
import com.example.p2pdemo.fragment.InvestFragment
import com.example.p2pdemo.fragment.MeFragment
import com.example.p2pdemo.fragment.MoreFragment
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener

class MainActivity : AppCompatActivity() {


        private var titles = listOf<String>("首页","投资","我的资产","更多")
        private var icons  = listOf<Int>(R.mipmap.home_select,R.mipmap.invest_select,R.mipmap.mine_select,R.mipmap.more_select)
        private var unicons  = listOf<Int>(R.mipmap.home_unselect,R.mipmap.inwest_unselect,R.mipmap.mine_unselect,R.mipmap.more_unselect)
        private lateinit var transaction:FragmentTransaction
        private var tabData = arrayListOf<CustomTabEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()

    }
    private var homeFragment:HomeFragment? = null
    private var investFragment:InvestFragment ? = null
    private var meFragment:MeFragment ? = null
    private var moreFragment:MoreFragment? = null
    private fun initTab() {
        for(i in titles.indices){
            tabData.add(TabAdapter(icons[i],unicons[i],titles[i]))
        }
        var tab_main = findViewById<CommonTabLayout>(R.id.tab_main)
        tab_main.setTabData(tabData)
        tab_main.textSelectColor = Color.BLUE
        tab_main.textUnselectColor = Color.parseColor("#8E8E8E")

        val fragmentManager = supportFragmentManager
            transaction = fragmentManager.beginTransaction()
        tab_main.setOnTabSelectListener(object : OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                hidFragment()
                when(position){
                    0 -> {
                        if (homeFragment == null){
                            homeFragment = HomeFragment()
                            transaction.add(R.id.fl_main, homeFragment!!)
                        }
                        transaction.show(homeFragment!!)
                    }

                    1 -> {
                        if (homeFragment == null){
                            investFragment = InvestFragment()
                            transaction.add(R.id.fl_main, investFragment!!)
                        }
                        transaction.show(investFragment!!)
                    }

                    2 -> {
                        if (meFragment == null){
                            meFragment = MeFragment()
                            transaction.add(R.id.fl_main, meFragment!!)
                        }
                        transaction.show(meFragment!!)
                    }

                    3 -> {
                        if (moreFragment == null){
                            moreFragment = MoreFragment()
                            transaction.add(R.id.fl_main, moreFragment!!)
                        }
                        transaction.show(moreFragment!!)
                    }
                }
                        transaction.commit()
                transaction = fragmentManager.beginTransaction()

            }

            override fun onTabReselect(position: Int) {

            }

        })
    }

    private fun hidFragment(){
        if (homeFragment != null) {
            transaction.hide(homeFragment!!)
        }
        if (investFragment != null) {
            transaction.hide(investFragment!!)
        }
        if (meFragment != null) {
            transaction.hide(meFragment!!)
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment!!)
        }
    }


}
