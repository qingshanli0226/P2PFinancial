package com.example.p_two_p



class MainActivity:BaseActivity() {
    override fun InitData() {
                val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main,Home_Fragment())
        beginTransaction.commit()
    }

    override fun InitTitle() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }



}