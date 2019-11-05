package com.example.modulebase

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import butterknife.ButterKnife
//Activity基类
abstract class BaseActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        initTitle()
        initData()
    }

    abstract fun initData()

    abstract fun initTitle()

    abstract fun  getLayoutId(): Int
}