package com.example.p_two_p

import com.example.sixp2p.BaseActivity
import com.example.sixp2p.R
import kotlinx.android.synthetic.main.activity_main.*
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity


class MainActivity: BaseActivity() {



    override fun InitData() {

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.Main_Fl,Home_Fragment())
        beginTransaction.show(Home_Fragment())
        beginTransaction.commit()




    }

    override fun InitView() {
        setContentView(R.layout.activity_main)
    }



    override fun InitTitle() {
        var mybarlist= mutableListOf<BottomNavigationEntity>()

        mybarlist.add(BottomNavigationEntity("首页",R.mipmap.bottom01,R.mipmap.bottom02))
        mybarlist.add(BottomNavigationEntity("投资",R.mipmap.bottom03,R.mipmap.bottom04))
        mybarlist.add(BottomNavigationEntity("首页",R.mipmap.bottom05,R.mipmap.bottom06))
        mybarlist.add(BottomNavigationEntity("首页",R.mipmap.bottom07,R.mipmap.bottom08))
//
        MyBar.setEntities(mybarlist)
//
//        MyBar.setBnbItemSelectListener(object :BottomNavigationBar.IBnbItemSelectListener{
//            override fun onBnbItemSelect(position: Int) {
//                when(position){
//
//                }
//            }
//        })
    }








}