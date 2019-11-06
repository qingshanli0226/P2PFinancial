package com.example.p2pdemo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.base.BaseActivity
import com.example.p2pdemo.Fragment.MainFragment.HomeFragment
import com.example.p2pdemo.Fragment.MainFragment.MoreFragment
import com.example.p2pdemo.R
import com.example.sixp2p.Fragment.MainFragment.InvestFragment
import com.example.sixp2p.Fragment.MainFragment.MyAssetsFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.sugarkawhi.bottomnavigationbar.BottomNavigationBar
import me.sugarkawhi.bottomnavigationbar.BottomNavigationEntity

class MainActivity :BaseActivity() {

    var homeFragment= HomeFragment()
    var insertFragment= InvestFragment()
    var myAssetsFragment= MyAssetsFragment()
    var moreFragment= MoreFragment()
    override fun InitData() {
        var beginTransaction= supportFragmentManager.beginTransaction()

        beginTransaction.replace(R.id.Main_Fl,HomeFragment())
        beginTransaction.commit()
    }

    //初始化视图
    override fun InitView() {
        setContentView(R.layout.activity_main)
    }



    override fun InitTitle() {
        var mybarlist= mutableListOf<BottomNavigationEntity>()

        mybarlist.add(BottomNavigationEntity("首页",R.mipmap.bottom01,R.mipmap.bottom02))
        mybarlist.add(BottomNavigationEntity("投资",R.mipmap.bottom03,R.mipmap.bottom04))
        mybarlist.add(BottomNavigationEntity("首页",R.mipmap.bottom05,R.mipmap.bottom06))
        mybarlist.add(BottomNavigationEntity("首页",R.mipmap.bottom07,R.mipmap.bottom08))
        MyBar.setEntities(mybarlist)
        MyBar.setBnbItemSelectListener(object : BottomNavigationBar.IBnbItemSelectListener{
            override fun onBnbItemSelect(position: Int) {
                when(position){
                    0->{
                        var beginTransaction= supportFragmentManager.beginTransaction()
                        beginTransaction.replace(R.id.Main_Fl,homeFragment)
                        beginTransaction.commit()
                    }

                    1->{
                        var beginTransaction= supportFragmentManager.beginTransaction()
                        beginTransaction.replace(R.id.Main_Fl,insertFragment)
                        beginTransaction.commit()


                    }
                    2->{
                        var beginTransaction= supportFragmentManager.beginTransaction()
                        beginTransaction.replace(R.id.Main_Fl,myAssetsFragment)
                        beginTransaction.commit()

                    }
                    3->{
                        var beginTransaction= supportFragmentManager.beginTransaction()
                        beginTransaction.replace(R.id.Main_Fl,moreFragment)
                        beginTransaction.commit()

                    }


                }


            }

        })




    }



}
