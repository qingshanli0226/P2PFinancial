package com.example.p2pmonthhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
    }

    private fun initFragments() {
        manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fl_main, Fragment_home1())
        transaction.add(R.id.fl_main, Fragment_home2())
        transaction.add(R.id.fl_main, Fragment_home3())
        transaction.add(R.id.fl_main, Fragment_home4())

        transaction.commit()
    }
}