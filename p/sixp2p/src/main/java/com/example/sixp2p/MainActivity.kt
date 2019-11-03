package com.example.p_two_p

import com.example.sixp2p.BaseActivity
import com.example.sixp2p.R


class MainActivity: BaseActivity() {
    override fun InitView() {
        setContentView(R.layout.activity_main)
    }

    override fun InitData() {
                val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.Main_Fl,Home_Fragment())
        beginTransaction.show(Home_Fragment())
        beginTransaction.commit()
    }

    override fun InitTitle() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }



}